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
 * �ڲ���������-ͼƬչʾ
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
		// ��ȡ���ݹ�����ͼƬ·��
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
	 * ����ҵ�������жϴ���
	 * 
	 * @param type
	 */
	public void dealImgByMsgType() {
		// �������������
		imgFile = new File(fileDir);
		showImgToImgView(fileDir);
	}

	/**
	 * ��ʾͼƬ
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
	 * ѹ��ͼƬ������ͼƬ
	 */
	public void compressPic() {
		try {
			plg = new ProgressDlg(ChatImageShow.this, "���ڴ������Ժ�...");
			plg.show();
			File outFile = new File(FilePath.sdCard
					+ FilePath.CHAT_IMAGE_PATH_NOSDCARD + imgFile.getName());
			File smallFile = new File(FilePath.sdCard
					+ FilePath.CHAT_IMAGE_PATH_NOSDCARD + imgFile.getName()
					+ "_small");
			// ������������Ƭ����ԭͼƬ��������ѹ������һ����ͼƬ
			imgFile.renameTo(outFile);
			picHelper.compressPic(outFile);
			smallPicHelper.compressPic(outFile, smallFile);
			outFile = null;
			smallFile = null;
			setResult(RESULT_OK);
			finish();
		} catch (OutOfMemoryError e) {
			L.d("����ͼƬʧ��OutOfMemoryError��" + e.getMessage());
		} catch (Exception e) {
			L.d("����ͼƬʧ��Exception��" + e.getMessage());
		}
	}

	public void recylcBitMap() {
		L.d("�ر�ͼƬ�鿴���ͷ��ڴ�");
		SysApp.getApp().recyleImageLoader();
		if (imgMap != null && !imgMap.isRecycled()) {
			imgMap.recycle();
			imgMap = null;
			L.d("�ر�ͼƬ�鿴������System.gc()");
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
			// ������أ�����ͼƬ���ر�ҳ��
			recylcBitMap();
			finish();
		}
		return true;
	}
}