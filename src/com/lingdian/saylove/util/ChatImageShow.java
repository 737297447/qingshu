package com.lingdian.saylove.util;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;

import com.lingdian.saylove.R;
import com.lingdian.saylove.SysApp;
import com.lingdian.saylove.tools.PictureCompressHelper;
import com.lingdian.saylove.util.common.FilePath;
import com.lingdian.saylove.util.common.ProgressDlg;
import com.nostra13.universalimageloader.utils.L;

/**
 * 内部互动聊天-图片展示
 * 
 * @author shenxy
 * 
 */
public class ChatImageShow extends Activity implements OnClickListener {

	private ImageView chat_img_show = null;

	private Bitmap imgMap;
	private String fileDir = "";
	private File imgFile;
	PictureCompressHelper picHelper = new PictureCompressHelper(720, 1280);
	PictureCompressHelper smallPicHelper = new PictureCompressHelper(100, 150);
	private ProgressDlg plg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat_show_image);
		findViewById();
		setListener();
		init();
	}

	public void findViewById() {
		chat_img_show = (ImageView) findViewById(R.id.chat_img_show);

	}

	public void setListener() {
		chat_img_show.setOnClickListener(this);
		chat_img_show.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {

				return false;
			}
		});
	}

	public void init() {
		// 获取传递过来的图片路径
		Intent intent = getIntent();
		fileDir = intent.getStringExtra("fileDir");
		System.out.println("fileDir------" + fileDir);
		dealImgByMsgType();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		default:
			break;
		}
	}

	/**
	 * 根据业务类型判断处理
	 * 
	 * @param type
	 */
	public void dealImgByMsgType() {
		// 如果是聊天拍照
		imgFile = new File(fileDir);
		showImgToImgView(fileDir);
	}

	/**
	 * 显示图片
	 * 
	 * @param type
	 * @param picPath
	 */
	public boolean showImgToImgView(String picPath) {
		if (TextUtils.isEmpty(picPath))
			return false;
		try {
			recylcBitMap();
			imgMap = picHelper.compressPic(picPath);
			if (imgMap != null) {
				chat_img_show.setImageBitmap(imgMap);
				return true;
			}
		} catch (OutOfMemoryError e) {

		}
		return false;
	}

	/**
	 * 压缩图片、处理图片
	 */
	public void compressPic() {
		try {
			plg = new ProgressDlg(ChatImageShow.this, "正在处理，请稍候...");
			plg.show();
			File outFile = new File(FilePath.sdCard
					+ FilePath.CHAT_IMAGE_PATH_NOSDCARD + imgFile.getName());
			File smallFile = new File(FilePath.sdCard
					+ FilePath.CHAT_IMAGE_PATH_NOSDCARD + imgFile.getName()
					+ "_small");
			// 如果是拍摄的照片，将原图片重命名，压缩生成一个新图片
			imgFile.renameTo(outFile);
			picHelper.compressPic(outFile);
			smallPicHelper.compressPic(outFile, smallFile);
			outFile = null;
			smallFile = null;
			setResult(RESULT_OK);
			finish();
		} catch (OutOfMemoryError e) {
			L.d("处理图片失败OutOfMemoryError：" + e.getMessage());
		} catch (Exception e) {
			L.d("处理图片失败Exception：" + e.getMessage());
		}
	}

	public void recylcBitMap() {
		L.d("关闭图片查看，释放内存");
		SysApp.getApp().recyleImageLoader();
		if (imgMap != null && !imgMap.isRecycled()) {
			imgMap.recycle();
			imgMap = null;
			L.d("关闭图片查看，调用System.gc()");
			System.gc();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 点击返回，回收图片、关闭页面
			recylcBitMap();
			finish();
		}
		return true;
	}
}