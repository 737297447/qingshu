package com.lingdian.saylove.pintu;

/**
 * @file Method.java
 * @brief ʵ��ƴͼ��Ϸ���桰��Ϸ˵������Activity
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
 * @brief ʵ�ֵ��ƴͼ��Ϸ���桰��Ϸ˵������ť����ת���Activity
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
     * @brief ���๦�ܣ����÷��ذ�ť�������������ť���ٵ�ǰActivity,����ƴͼ��ϷActivity
     */
	class backOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
		}
		
	}
}