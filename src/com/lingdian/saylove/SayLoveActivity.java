package com.lingdian.saylove;

import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lingdian.saylove.R;
import com.lingdian.saylove.database.DBHelper;
import com.lingdian.saylove.database.DBLawOperator;

public class SayLoveActivity extends Activity {

	private RelativeLayout relativeLayout = null;
	private AnimationDrawable anim = null;
	private ImageButton bt1;
	private String[] phone;
	private String[] content;
	DBLawOperator dbLawOper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_saylove);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		db = DBHelper.getDBInstance(this.getApplicationContext());
		dbLawOper = new DBLawOperator();

		relativeLayout = (RelativeLayout) findViewById(R.id.linearLayout);
		relativeLayout.getViewTreeObserver().addOnPreDrawListener(onpdl);
		bt1 = (ImageButton) findViewById(R.id.bt1);
		AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation1.setDuration(2000);
		alphaAnimation1.setStartOffset(18000);
		bt1.startAnimation(alphaAnimation1);

		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				sendMessage();
				bt1.setVisibility(View.GONE);
			}
		});

	}

	OnPreDrawListener onpdl = new OnPreDrawListener() {
		@Override
		public boolean onPreDraw() {
			Object ob = relativeLayout.getBackground();
			anim = (AnimationDrawable) ob;
			anim.start();
			return true;
		}
	};

	/**
	 * ���Ͷ���
	 */
	private void sendMessage() {
		Cursor cursor = db.rawQuery("select * from 	Mms where single='Gril'",
				null);
		phone = new String[cursor.getCount()];
		content = new String[cursor.getCount()];
		int i = 0;
		if (!cursor.moveToNext()) {
			Toast.makeText(SayLoveActivity.this, "����ϵ����Ǹ���/����Ϊʲôû����˵��������˵���黰",
					Toast.LENGTH_LONG).show();

		}
		else {
			phone[i] = cursor.getString(0);
			content[i] = cursor.getString(1);
			// �������Ź�����
			SmsManager smsManager = SmsManager.getDefault();
			// �����������70,���ֳɶ������ŷ���
			if (content[i].length() > 70) {
				// ��������Ϣ���в��
				List<String> msgs = smsManager.divideMessage(content[i]);
				// ѭ�����Ͷ���
				for (String msg : msgs) {
					// ���Ͷ���
					smsManager.sendTextMessage(phone[i], null, msg, null, null);
				}
			} else {
				smsManager.sendTextMessage(phone[i], null, content[i], null,
						null);
			}
			Toast.makeText(SayLoveActivity.this,
					"�������ó�����ֻ����鿴���ţ������յ����ţ�Ҳ���������ҵ����⣡����", Toast.LENGTH_LONG)
					.show();
		}
	}

}
