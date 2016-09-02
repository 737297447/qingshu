package com.lingdian.saylove.util.common;


import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.lingdian.saylove.R;
import com.lingdian.saylove.util.ChatImageShow;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class DialogUtils {
	public static final int PHOTO_CAMERA = 10001;// ����
	public static final int PHOTO_PICTURE = 10002;// �������ѡ��ͼƬ
	public static final int VIDEO_RECORD = 10003;// ¼��
	public static final int SDFILE_RECORD = 10004;// SD����ѡ�񸽼�
	public static final int SDFILE_VIDEO = 10005;// ϵͳ��ѡ����Ƶ

	public static void showToast(Activity activity, String str) {
		Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
	}

	public static void showPicture(Activity activity, String folderPath) {
		Intent intent = new Intent(activity, ChatImageShow.class);
		intent.putExtra("fileDir", folderPath);
		activity.startActivity(intent);
	}

	public static String showGetPicDialog(final Activity activity) {
		final String photoPath = FilePath.SAVE_PHOTO_FILE_TOSD + StringUtils.getlongDate() + ".jpg";
		ChooseDialog choDlg = new ChooseDialog(activity, R.style.MyDialogStyleTop, "ѡ����Դ", "���", "ͼƬ��") {
			@Override
			public void doBtn1Click() {
				this.dismiss();
				FileUtils.createFile(FilePath.SAVE_PHOTO_FILE_TOSD);
				Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(photoPath)));
				activity.startActivityForResult(intent, PHOTO_CAMERA);
			}

			@Override
			public void doBtn2Click() {
				this.dismiss();
				Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				activity.startActivityForResult(intent, PHOTO_PICTURE);
			}
		};
		choDlg.show();
		return photoPath;
	}
	
	/**ֱ�ӿ���ͼƬѡ��*/
	public static void showChoosePic(final Activity activity){
		Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		activity.startActivityForResult(intent, PHOTO_PICTURE);
	}
	
	/**ֱ�ӿ�������*/
	public static String showChooseTakePic(final Activity activity){
		String photoPath = FilePath.SAVE_PHOTO_FILE_TOSD + StringUtils.getlongDate() + ".jpg";
		FileUtils.createFile(FilePath.SAVE_PHOTO_FILE_TOSD);
		Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(photoPath)));
		activity.startActivityForResult(intent, PHOTO_CAMERA);
		return photoPath;
	}

	/***
	 * ����ϵͳѡ����Ƶ
	 * @param activity
	 */
	public static void showGetVideo(final Activity activity) {
		Intent picIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
		activity.startActivityForResult(picIntent, SDFILE_VIDEO);
	}

	public static String showGetVideoDialog(Activity activity) {
		String videoPath = "";
//		if (Build.VERSION.SDK_INT < 14) {
//			showToast(activity, "ϵͳ�汾����4.0���ݲ�֧����Ƶ���㹦��");
//			return "";
//		}
//		Intent intent = new Intent(activity, VideoRecordingActivity.class);
//		videoPath = StorageUtils.getFileName();
//		intent.putExtra("video_file", videoPath);
//		activity.startActivityForResult(intent, VIDEO_RECORD);
//		activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
		return videoPath;
	}


	public interface DialogCallback {
		public void PositiveButton(int i);
	}

	// �������������ʱ ��������ʧȥ����
	public static boolean checkKeyboardShowing(Context activity, View view, EditText editView) {
		view.requestFocus();
		InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		boolean active = imm.isActive(editView);
		imm.hideSoftInputFromWindow(editView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

		editView.clearFocus();
		return active;
	}
}
