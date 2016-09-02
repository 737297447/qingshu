package com.lingdian.saylove;

import java.io.File;

import com.lingdian.saylove.pintu.SePintuActivity;
import com.lingdian.saylove.util.common.FilePath;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class ILoveYouActivity extends Activity {

	private ImageView I1, I2, I3, I4, I5, I6, I7, I8, I9, I10, I11, I12, I13,
			I14, I15;

	private ImageView love1, love2, love3, love4, love5, love6, love7, love8,
			love9, love10, love11, love12, love13, love14, love15, love16,
			love17, love18;
	private ImageView U1, U2, U3, U4, U5, U6, U7, U8, U9, U10, U11, U12, U13,
			U14, U15, U16, U17, U18, U19;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		initViewI();
		initViewLove();
		initViewU();
		showAnimationI();
		showAnimationLove();
		showAnimationU();
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(ILoveYouActivity.this, SayLoveActivity.class);
				startActivity(intent);
				ILoveYouActivity.this.finish();
			}
		}, 25500);
	}


	public void initViewI() {
		// 一起出现
		I1 = (ImageView) findViewById(R.id.love1);
		I4 = (ImageView) findViewById(R.id.love3);

		I2 = (ImageView) findViewById(R.id.love14);
		I5 = (ImageView) findViewById(R.id.ImageView11);

		I3 = (ImageView) findViewById(R.id.love13);
		I6 = (ImageView) findViewById(R.id.love2);

		// 逐次出现
		I7 = (ImageView) findViewById(R.id.love12);
		I8 = (ImageView) findViewById(R.id.love11);
		I9 = (ImageView) findViewById(R.id.love10);
		I10 = (ImageView) findViewById(R.id.love9);
		I11 = (ImageView) findViewById(R.id.love8);
		I12 = (ImageView) findViewById(R.id.love7);
		I13 = (ImageView) findViewById(R.id.love6);
		I14 = (ImageView) findViewById(R.id.love5);
		I15 = (ImageView) findViewById(R.id.love4);
	}

	public void initViewLove() {
		// 顺时针画
		love1 = (ImageView) findViewById(R.id.img8);
		love2 = (ImageView) findViewById(R.id.img6);
		love3 = (ImageView) findViewById(R.id.img4);
		love4 = (ImageView) findViewById(R.id.img2);
		love5 = (ImageView) findViewById(R.id.ImageView02);
		love6 = (ImageView) findViewById(R.id.ImageView03);
		love7 = (ImageView) findViewById(R.id.ImageView05);
		love8 = (ImageView) findViewById(R.id.ImageView06);
		love9 = (ImageView) findViewById(R.id.ImageView09);
		love10 = (ImageView) findViewById(R.id.ImageView10);
		love11 = (ImageView) findViewById(R.id.ImageView08);
		love12 = (ImageView) findViewById(R.id.ImageView07);
		love13 = (ImageView) findViewById(R.id.ImageView04);
		love14 = (ImageView) findViewById(R.id.ImageView01);
		love15 = (ImageView) findViewById(R.id.img1);
		love16 = (ImageView) findViewById(R.id.img3);
		love17 = (ImageView) findViewById(R.id.img5);
		love18 = (ImageView) findViewById(R.id.img7);
	}

	public void initViewU() {
		U1 = (ImageView) findViewById(R.id.ImageView13);
		U2 = (ImageView) findViewById(R.id.ImageView12);
		U3 = (ImageView) findViewById(R.id.ImageView16);
		U4 = (ImageView) findViewById(R.id.ImageView17);
		U5 = (ImageView) findViewById(R.id.ImageView15);
		U6 = (ImageView) findViewById(R.id.ImageView14);

		U7 = (ImageView) findViewById(R.id.ImageView18);
		U8 = (ImageView) findViewById(R.id.ImageView20);
		U9 = (ImageView) findViewById(R.id.ImageView22);
		U10 = (ImageView) findViewById(R.id.ImageView24);
		U11 = (ImageView) findViewById(R.id.ImageView26);
		U12 = (ImageView) findViewById(R.id.ImageView28);
		U13 = (ImageView) findViewById(R.id.ImageView30);
		U14 = (ImageView) findViewById(R.id.ImageView29);
		U15 = (ImageView) findViewById(R.id.ImageView27);
		U16 = (ImageView) findViewById(R.id.ImageView25);
		U17 = (ImageView) findViewById(R.id.ImageView23);
		U18 = (ImageView) findViewById(R.id.ImageView21);
		U19 = (ImageView) findViewById(R.id.ImageView19);
	}

	public void showAnimationI() {

		AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation1.setDuration(2000);
		alphaAnimation1.setStartOffset(500);
		I1.startAnimation(alphaAnimation1);
		I4.startAnimation(alphaAnimation1);

		AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation2.setDuration(2000);
		alphaAnimation2.setStartOffset(1000);
		I2.startAnimation(alphaAnimation2);
		I5.startAnimation(alphaAnimation2);

		AlphaAnimation alphaAnimation3 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation3.setDuration(2000);
		alphaAnimation3.setStartOffset(1500);
		I3.startAnimation(alphaAnimation3);
		I6.startAnimation(alphaAnimation3);

		AlphaAnimation alphaAnimation4 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation4.setDuration(2000);
		alphaAnimation4.setStartOffset(2000);
		I7.startAnimation(alphaAnimation4);
		AlphaAnimation alphaAnimation5 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation5.setDuration(2000);
		alphaAnimation5.setStartOffset(2500);
		I8.startAnimation(alphaAnimation5);
		AlphaAnimation alphaAnimation6 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation6.setDuration(2000);
		alphaAnimation6.setStartOffset(3000);
		I9.startAnimation(alphaAnimation6);
		AlphaAnimation alphaAnimation7 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation7.setDuration(2000);
		alphaAnimation7.setStartOffset(3500);
		I10.startAnimation(alphaAnimation7);
		AlphaAnimation alphaAnimation8 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation8.setDuration(2000);
		alphaAnimation8.setStartOffset(4000);
		I11.startAnimation(alphaAnimation8);
		AlphaAnimation alphaAnimation9 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation9.setDuration(2000);
		alphaAnimation9.setStartOffset(4500);
		I12.startAnimation(alphaAnimation9);
		AlphaAnimation alphaAnimation10 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation10.setDuration(2000);
		alphaAnimation10.setStartOffset(5000);
		I13.startAnimation(alphaAnimation10);
		AlphaAnimation alphaAnimation11 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation11.setDuration(2000);
		alphaAnimation11.setStartOffset(5500);
		I14.startAnimation(alphaAnimation11);
		AlphaAnimation alphaAnimation12 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation12.setDuration(2000);
		alphaAnimation12.setStartOffset(6000);
		I15.startAnimation(alphaAnimation12);

	}

	public void showAnimationLove() {

		AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation1.setDuration(2000);
		alphaAnimation1.setStartOffset(6500);
		love1.startAnimation(alphaAnimation1);

		AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation2.setDuration(2000);
		alphaAnimation2.setStartOffset(7000);
		love2.startAnimation(alphaAnimation2);

		AlphaAnimation alphaAnimation3 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation3.setDuration(2000);
		alphaAnimation3.setStartOffset(7500);
		love3.startAnimation(alphaAnimation3);

		AlphaAnimation alphaAnimation4 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation4.setDuration(2000);
		alphaAnimation4.setStartOffset(8000);
		love4.startAnimation(alphaAnimation4);

		AlphaAnimation alphaAnimation5 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation5.setDuration(2000);
		alphaAnimation5.setStartOffset(8500);
		love5.startAnimation(alphaAnimation5);

		AlphaAnimation alphaAnimation6 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation6.setDuration(2000);
		alphaAnimation6.setStartOffset(9000);
		love6.startAnimation(alphaAnimation6);

		AlphaAnimation alphaAnimation7 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation7.setDuration(2000);
		alphaAnimation7.setStartOffset(9500);
		love7.startAnimation(alphaAnimation7);

		AlphaAnimation alphaAnimation8 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation8.setDuration(2000);
		alphaAnimation8.setStartOffset(10000);
		love8.startAnimation(alphaAnimation8);

		AlphaAnimation alphaAnimation9 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation9.setDuration(2000);
		alphaAnimation9.setStartOffset(10500);
		love9.startAnimation(alphaAnimation9);

		AlphaAnimation alphaAnimation10 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation10.setDuration(2000);
		alphaAnimation10.setStartOffset(11000);
		love10.startAnimation(alphaAnimation10);

		AlphaAnimation alphaAnimation11 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation11.setDuration(2000);
		alphaAnimation11.setStartOffset(11500);
		love11.startAnimation(alphaAnimation11);

		AlphaAnimation alphaAnimation12 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation12.setDuration(2000);
		alphaAnimation12.setStartOffset(12000);
		love12.startAnimation(alphaAnimation12);

		AlphaAnimation alphaAnimation13 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation13.setDuration(2000);
		alphaAnimation13.setStartOffset(12500);
		love13.startAnimation(alphaAnimation13);

		AlphaAnimation alphaAnimation14 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation14.setDuration(2000);
		alphaAnimation14.setStartOffset(13000);
		love14.startAnimation(alphaAnimation14);

		AlphaAnimation alphaAnimation15 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation15.setDuration(2000);
		alphaAnimation15.setStartOffset(13500);
		love15.startAnimation(alphaAnimation15);

		AlphaAnimation alphaAnimation16 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation16.setDuration(2000);
		alphaAnimation16.setStartOffset(14000);
		love16.startAnimation(alphaAnimation16);

		AlphaAnimation alphaAnimation17 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation17.setDuration(2000);
		alphaAnimation17.setStartOffset(14500);
		love17.startAnimation(alphaAnimation17);

		AlphaAnimation alphaAnimation18 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation18.setDuration(2000);
		alphaAnimation18.setStartOffset(15000);
		love18.startAnimation(alphaAnimation18);
	}

	public void showAnimationU() {
		AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation1.setDuration(2000);
		alphaAnimation1.setStartOffset(15500);
		U1.startAnimation(alphaAnimation1);

		AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation2.setDuration(2000);
		alphaAnimation2.setStartOffset(16000);
		U2.startAnimation(alphaAnimation2);

		AlphaAnimation alphaAnimation3 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation3.setDuration(2000);
		alphaAnimation3.setStartOffset(16500);
		U3.startAnimation(alphaAnimation3);

		AlphaAnimation alphaAnimation4 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation4.setDuration(2000);
		alphaAnimation4.setStartOffset(17000);
		U4.startAnimation(alphaAnimation4);

		AlphaAnimation alphaAnimation5 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation5.setDuration(2000);
		alphaAnimation5.setStartOffset(17500);
		U5.startAnimation(alphaAnimation5);

		AlphaAnimation alphaAnimation6 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation6.setDuration(2000);
		alphaAnimation6.setStartOffset(18000);
		U6.startAnimation(alphaAnimation6);

		AlphaAnimation alphaAnimation7 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation7.setDuration(2000);
		alphaAnimation7.setStartOffset(18500);
		U7.startAnimation(alphaAnimation7);

		AlphaAnimation alphaAnimation8 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation8.setDuration(2000);
		alphaAnimation8.setStartOffset(19000);
		U8.startAnimation(alphaAnimation8);

		AlphaAnimation alphaAnimation9 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation9.setDuration(2000);
		alphaAnimation9.setStartOffset(19500);
		U9.startAnimation(alphaAnimation9);

		AlphaAnimation alphaAnimation10 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation10.setDuration(2000);
		alphaAnimation10.setStartOffset(20000);
		U10.startAnimation(alphaAnimation10);

		AlphaAnimation alphaAnimation11 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation11.setDuration(2000);
		alphaAnimation11.setStartOffset(20500);
		U11.startAnimation(alphaAnimation11);

		AlphaAnimation alphaAnimation12 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation12.setDuration(2000);
		alphaAnimation12.setStartOffset(21000);
		U12.startAnimation(alphaAnimation12);

		AlphaAnimation alphaAnimation13 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation13.setDuration(2000);
		alphaAnimation13.setStartOffset(21500);
		U13.startAnimation(alphaAnimation13);

		AlphaAnimation alphaAnimation14 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation14.setDuration(2000);
		alphaAnimation14.setStartOffset(22000);
		U14.startAnimation(alphaAnimation14);

		AlphaAnimation alphaAnimation15 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation15.setDuration(2000);
		alphaAnimation15.setStartOffset(22500);
		U15.startAnimation(alphaAnimation15);

		AlphaAnimation alphaAnimation16 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation16.setDuration(2000);
		alphaAnimation16.setStartOffset(23000);
		U16.startAnimation(alphaAnimation16);

		AlphaAnimation alphaAnimation17 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation17.setDuration(2000);
		alphaAnimation17.setStartOffset(23500);
		U17.startAnimation(alphaAnimation17);

		AlphaAnimation alphaAnimation18 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation18.setDuration(2000);
		alphaAnimation18.setStartOffset(24000);
		U18.startAnimation(alphaAnimation18);

		AlphaAnimation alphaAnimation19 = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation19.setDuration(2000);
		alphaAnimation19.setStartOffset(24500);
		U19.startAnimation(alphaAnimation19);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			System.exit(0);
		}

		return super.onKeyDown(keyCode, event);
	}

}
