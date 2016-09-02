package com.lingdian.saylove.pintu;

/**
 * @file Method.java
 * @brief 实现拼图游戏里面“游戏说明”的Activity
 * @author zhoujun
 * @version V1.0.00
 * @date 2012/09/12
 * Blog: http://blog.csdn.net/jjzhoujun2010
 */


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lingdian.saylove.R;

/**
 * @brief 实现点击拼图游戏里面“游戏说明”按钮后跳转后的Activity
 * */
public class DetailPintuActivity extends Activity{
	Button mBackBtn = null;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_pintu);
		mBackBtn = (Button)findViewById(R.id.backBtn);
		mBackBtn.setOnClickListener(new backOnClickListener());
	}
    /**
     * @brief 本类功能：设置返回按钮监听器，点击按钮销毁当前Activity,返回拼图游戏Activity
     */
	class backOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
		
	}
}