package com.lingdian.saylove.pintu;

/**
 * @file SourceImageAty.java
 * @brief 显示未打乱的原图
 * @author zhoujun
 * @version V1.0.00
 * @date 2012/09/12
 * Blog: http://blog.csdn.net/jjzhoujun2010
 */


import java.io.File;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.lingdian.saylove.R;
import com.lingdian.saylove.tools.FileChange;
import com.lingdian.saylove.util.common.FilePath;
/**
 * @brief 显示未打乱的原图 
 * */
public class SourceImageActivity extends Activity{
	private ImageView mPic1;
	private Bitmap mBitmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_source);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mPic1 = (ImageView)findViewById(R.id.pic1);
		
		int randomInt = getIntent().getIntExtra("randomInt", 0);
		File file = new File(FilePath.USER_IMAGE_PATH);
		File[] jpgFiles = file.listFiles(FileChange
				.getFileExtensionFilter(".jpg"));
		if (jpgFiles.length == 0) {
			mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic1);
		} else {
			mBitmap = FileChange.getBitmap(this,FilePath.USER_IMAGE_PATH
						+ jpgFiles[randomInt].getName());
		}
		
		mPic1.setImageBitmap(mBitmap);
		mPic1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();	//点击屏幕图片即退出当前Activity
			}
			
		});
		
	}
}
