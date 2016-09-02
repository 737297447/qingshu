package com.lingdian.saylove.util.common;


import java.io.File;
import java.text.DecimalFormat;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.View;

import com.lingdian.saylove.R;
import com.lingdian.saylove.SysApp;
import com.nostra13.universalimageloader.utils.L;
/**
 * 图片点击监听，跳转到图片查看界面
 * @author 
 *
 */
public class ImageClickListener implements View.OnClickListener {
	
	private String filePath;
	private Activity activity;
	
	private String fileKey;
	
	public ImageClickListener(Activity activity,String filePath){
		this.activity = activity;
		this.filePath = filePath;
	}
	
	public ImageClickListener(Activity activity,String filePath,String fileKey){
		this.activity = activity;
		this.filePath = filePath;
		this.fileKey = fileKey;
	}
	
	public void onClick(View v) {
			DialogUtils.showPicture(activity,filePath);
	}
	
}