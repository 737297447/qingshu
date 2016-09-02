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

public class ThreeFragment extends BaseFragment {

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
		View v = inflater.inflate(R.layout.fragment_three, null);
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
		Cursor cursor = db.rawQuery("select * from 	Xiangzhi where single='Gril'",
				null);
		sqlTime = new String[cursor.getCount()];
		sqlGanshou = new String[cursor.getCount()];
		int i = 0;
		if (!cursor.moveToNext()) {
			first_time.setText("相知在秋天");
			dazi = "有缘就会相遇。因为有了与你的相遇，我的生活融入了更多的活力。相识，注定是一场美丽的邂逅；相知，注定是一种情感的难舍。冥冥之中注定的这份缘，让相隔遥远的你我相遇，从此我的记忆再也抹不去你的影子。";
		}else{
			sqlTime[i] = cursor.getString(0);
			sqlGanshou[i] = cursor.getString(1);
			if (sqlGanshou[i].length() > 0) {
				dazi = sqlGanshou[i];
			} else {
				dazi = "有缘就会相遇。因为有了与你的相遇，我的生活融入了更多的活力。相识，注定是一场美丽的邂逅；相知，注定是一种情感的难舍。冥冥之中注定的这份缘，让相隔遥远的你我相遇，从此我的记忆再也抹不去你的影子。";
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
			iWorkImageView.setImageResource(R.drawable.first_03);
		} else {

			Bitmap mBitmap = BitmapFactory.decodeFile(FilePath.USER_IMAGE_PATH
					+ jpgFiles[(int) (Math.random() * jpgFiles.length + 0)]
							.getName());
			iWorkImageView.setImageBitmap(mBitmap);
		}
		}else{
			iWorkImageView.setImageResource(R.drawable.first_03);
		}
	}
}
