package com.lingdian.saylove.first.fragment;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lingdian.saylove.R;
import com.lingdian.saylove.database.DBHelper;
import com.lingdian.saylove.database.DBLawOperator;
import com.lingdian.saylove.tools.CheckWorkImageView;
import com.lingdian.saylove.tools.FileChange;
import com.lingdian.saylove.util.common.FilePath;

public class OneFragment extends BaseFragment {

	private TextView first_time;
	private CheckWorkImageView iWorkImageView;
	private Context context;
	private TextView dazi_xiaoguo;

	private String dazi;

	int textIndex = 0;

	DBLawOperator dbLawOper;
	SQLiteDatabase db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		db = DBHelper.getDBInstance(context);
		dbLawOper = new DBLawOperator();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		context = getActivity();
		View v = inflater.inflate(R.layout.fragment_one, null);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
	}
	Handler handler1 = new Handler();
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 要做的事情
			handler1.postDelayed(this, 200);
			if (textIndex <= dazi.length()) {
				String subText = dazi.substring(0, textIndex);
				dazi_xiaoguo.setText(subText);
				textIndex++;
			} else if (textIndex > dazi.length()) {
				System.out.println("停止");
				handler1.removeCallbacks(runnable);
				dazi_xiaoguo.setText(dazi);
			}
		}
	};

	@SuppressLint("NewApi")
	public void initView() {
		first_time = (TextView) this.getView().findViewById(R.id.first_time);
		dazi_xiaoguo = (TextView) this.getView()
				.findViewById(R.id.dazi_xiaoguo);
		Cursor cursor = db.rawQuery("select * from 	Xiangyu where single='Gril'",
				null);
		sqlTime = new String[cursor.getCount()];
		sqlGanshou = new String[cursor.getCount()];
		int i = 0;
		if (!cursor.moveToNext()) {
			first_time.setText("相遇在春天");
			dazi = "遇见你是最美丽的意外！我感谢上天，送给我一个这样好的礼物！或许遇见你的那一刻，你微眯的眼眸里并没有看到我，但我明白，我遇见了你就一定有后来！！！！ ";
		}else{
			sqlTime[i] = cursor.getString(0);
			sqlGanshou[i] = cursor.getString(1);
			if (sqlGanshou[i].length() > 0) {
				dazi = sqlGanshou[i];
			} else {
				dazi = "遇见你是最美丽的意外！我感谢上天，送给我一个这样好的礼物！或许遇见你的那一刻，你微眯的眼眸里并没有看到我，但我明白，我遇见了你就一定有后来！！！！ ";
			}
			first_time.setText(sqlTime[i]);
		}
		handler1.postDelayed(runnable, 200);

		iWorkImageView = (CheckWorkImageView) this.getView().findViewById(
				R.id.first_img);
		File file = new File(FilePath.USER_IMAGE_PATH);
		File[] jpgFiles = file.listFiles(FileChange
				.getFileExtensionFilter(".jpg"));
		if(jpgFiles != null){
		if (jpgFiles.length == 0) {
			iWorkImageView.setImageResource(R.drawable.first_01);
		} else {

			Bitmap mBitmap = BitmapFactory.decodeFile(FilePath.USER_IMAGE_PATH
					+ jpgFiles[(int) (Math.random() * jpgFiles.length + 0)]
							.getName());
			iWorkImageView.setImageBitmap(mBitmap);
		}
		}else{
			iWorkImageView.setImageResource(R.drawable.first_01);
		}
	}
}
