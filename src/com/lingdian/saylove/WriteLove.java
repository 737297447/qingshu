package com.lingdian.saylove;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lingdian.saylove.database.DBHelper;
import com.lingdian.saylove.database.DBLawOperator;
import com.lingdian.saylove.tongxunlu.ContextActivity;
import com.lingdian.saylove.tools.CopyFile;
import com.lingdian.saylove.tools.PictureCompressHelper;
import com.lingdian.saylove.tools.dialog.ScreenInfo;
import com.lingdian.saylove.tools.dialog.WheelMain;
import com.lingdian.saylove.util.FileChooseAndShowView;
import com.lingdian.saylove.util.common.FilePath;
import com.lingdian.saylove.util.common.TalkVoice;
import com.lingdian.saylove.util.file.FileModels;

public class WriteLove extends Activity implements OnClickListener {

	private EditText telphone, neirong;
	private Button tongxunlu, save;
	private String phone, content;
	private SQLiteDatabase db;
	private DBLawOperator dbLawOper;
	private String telphonenum;

	public PictureCompressHelper pHelper;

	public PictureCompressHelper picHelper = new PictureCompressHelper(720,
			1280);

	/** 附件选择控件 */
	private FileChooseAndShowView file_choose_and_show_view;
	private TalkVoice talkVoice;
	/** 附件列表 **/
	protected List<FileModels> fileMode_list = new ArrayList<FileModels>();

	private RelativeLayout xiangyu_time_layout;
	private RelativeLayout xiangshi_time_layout;
	private RelativeLayout xiangzhi_time_layout;
	private RelativeLayout xindong_time_layout;

	private TextView xiangyu_time_text;
	private TextView xiangshi_time_text;
	private TextView xiangzhi_time_text;
	private TextView xindong_time_text;

	private TextView xiangyu_ganshou_text;
	private TextView xiangshi_ganshou_text;
	private TextView xiangzhi_ganshou_text;
	private TextView xindong_ganshou_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_writelove);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		db = DBHelper.getDBInstance(this.getApplicationContext());
		dbLawOper = new DBLawOperator();
		pHelper = new PictureCompressHelper();
		getCalendar();
		initView();
		initData();
	}

	public void initView() {
		telphone = (EditText) findViewById(R.id.telphone);
		neirong = (EditText) findViewById(R.id.neirong);
		tongxunlu = (Button) findViewById(R.id.tongxunlu);

		xiangyu_time_layout = (RelativeLayout) findViewById(R.id.xiangyu_time_layout);
		xiangshi_time_layout = (RelativeLayout) findViewById(R.id.xiangshi_time_layout);
		xiangzhi_time_layout = (RelativeLayout) findViewById(R.id.xiangzhi_time_layout);
		xindong_time_layout = (RelativeLayout) findViewById(R.id.xindong_time_layout);

		xiangyu_time_text = (TextView) findViewById(R.id.xiangyu_time_text);
		xiangshi_time_text = (TextView) findViewById(R.id.xiangshi_time_text);
		xiangzhi_time_text = (TextView) findViewById(R.id.xiangzhi_time_text);
		xindong_time_text = (TextView) findViewById(R.id.xindong_time_text);

		xiangyu_ganshou_text = (TextView) findViewById(R.id.xiangyu_ganshou_text);
		xiangshi_ganshou_text = (TextView) findViewById(R.id.xiangshi_ganshou_text);
		xiangzhi_ganshou_text = (TextView) findViewById(R.id.xiangzhi_ganshou_text);
		xindong_ganshou_text = (TextView) findViewById(R.id.xindong_ganshou_text);

		xiangyu_time_layout.setOnClickListener(this);
		xiangshi_time_layout.setOnClickListener(this);
		xiangzhi_time_layout.setOnClickListener(this);
		xindong_time_layout.setOnClickListener(this);

		save = (Button) findViewById(R.id.save);
		tongxunlu.setOnClickListener(this);
		save.setOnClickListener(this);
		file_choose_and_show_view = (FileChooseAndShowView) findViewById(R.id.file_choose_and_show_view);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void initData() {
		talkVoice = new TalkVoice();
		file_choose_and_show_view.init(this, talkVoice, fileMode_list);
	}

	// 匹配电话号码
	protected boolean matchPhone(String text) {
		if (Pattern.compile("(\\d{11})|(\\+\\d{3,})").matcher(text).matches()) {
			return true;
		}
		return false;
	}

	private boolean validateAccount() {
		telphonenum = null;

		String account = telphone.getText().toString().trim();
		if (matchPhone(account)) {
			if (account.length() < 3) {
				Toast.makeText(WriteLove.this, "账号格式不正确", Toast.LENGTH_SHORT)
						.show();
				telphone.requestFocus();
				return false;
			}
			if (Pattern.compile("(\\d{3,})|(\\+\\d{3,})").matcher(account)
					.matches()) {
				telphonenum = account;
				return true;
			}
		}

		Toast.makeText(WriteLove.this, "账号格式不正确", Toast.LENGTH_SHORT).show();
		telphone.requestFocus();
		return false;

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (db != null && db.isOpen()) {
			db.close();
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.save:
			phone = telphone.getText().toString();
			content = neirong.getText().toString();
			// 判断手机号码和短信内容不能为空
			if (phone.equals("") && content.equals("")) {
				Toast.makeText(WriteLove.this,
						"电话号码、短信内容和她/他的照片都不能为空，请选择完成在点击保存", Toast.LENGTH_LONG)
						.show();
				return;
			}
//			if(xiangyu_time_text.getText().toString().equals("")){
//				Toast.makeText(WriteLove.this,
//						"相遇时间不能为空，请点击选择时间，如果不记得请选择一个大概时间", Toast.LENGTH_LONG)
//						.show();
//				return;
//			}
//			if(xiangshi_time_text.getText().toString().equals("")){
//				Toast.makeText(WriteLove.this,
//						"相识时间不能为空，请点击选择时间，如果不记得请选择一个大概时间", Toast.LENGTH_LONG)
//						.show();
//				return;
//			}
//			if(xiangzhi_time_text.getText().toString().equals("")){
//				Toast.makeText(WriteLove.this,
//						"相知时间不能为空，请点击选择时间，如果不记得请选择一个大概时间", Toast.LENGTH_LONG)
//						.show();
//				return;
//			}
//			if(xindong_time_text.getText().toString().equals("")){
//				Toast.makeText(WriteLove.this,
//						"心动时间不能为空，请点击选择时间，如果不记得请选择一个大概时间", Toast.LENGTH_LONG)
//						.show();
//				return;
//			}

			if (!validateAccount()) {
				return;
			} else {
				File allVolceFile = new File(FilePath.USER_MIC_PATH);
				CopyFile.DeleteFile(allVolceFile);
				File allImageFile = new File(FilePath.USER_IMAGE_PATH);
				CopyFile.DeleteFile(allImageFile);

				Cursor cursor = db.rawQuery("select * from Mms ", null);
				if (!cursor.moveToNext()) {
					dbLawOper.insert(db, "Mms", phone, content, "Gril");
					dbLawOper.insert(db, "Xiangyu", xiangyu_time_text.getText()
							.toString(), xiangyu_ganshou_text.getText()
							.toString(), "Gril");
					dbLawOper.insert(db, "Xiangshi", xiangshi_time_text
							.getText().toString(), xiangshi_ganshou_text
							.getText().toString(), "Gril");
					dbLawOper.insert(db, "Xiangzhi", xiangzhi_time_text
							.getText().toString(), xiangzhi_ganshou_text
							.getText().toString(), "Gril");
					dbLawOper.insert(db, "Xindong", xindong_time_text.getText()
							.toString(), xindong_ganshou_text.getText()
							.toString(), "Gril");

					for (int i = 0; i < fileMode_list.size(); i++) {
						if (fileMode_list.get(i).getAliasfilename()
								.equals("你的表白")) {
							File oldFile = new File(fileMode_list.get(i)
									.getFilepath());
							File newFile = new File(FilePath.USER_MIC_PATH
									+ "/biaobaiVolce" + i + ".amr");
							CopyFile.copyfile(oldFile, newFile, true);
						} else if (fileMode_list.get(i).getAliasfilename()
								.equals("你的女神照片")) {
							File oldFile = new File(fileMode_list.get(i)
									.getFilepath());
							picHelper.compressPic(oldFile);
							File newFile = new File(FilePath.USER_IMAGE_PATH
									+ "/biaobaiImage" + i + ".jpg");
							CopyFile.copyfile(oldFile, newFile, true);
						}
					}
					Toast.makeText(WriteLove.this, "数据插入成功", Toast.LENGTH_LONG)
							.show();

					File alloldVolceFile = new File(FilePath.SAVE_MIC_PATH_TOSD);
					CopyFile.DeleteFile(alloldVolceFile);
					File alloldImageFile = new File(
							FilePath.SAVE_IMAGELOAD_CACHE_PATH);
					CopyFile.DeleteFile(alloldImageFile);

				} else {
					dbLawOper.update(db, "Mms", phone, content);
					dbLawOper.update(db, "Xiangyu", xiangyu_time_text.getText()
							.toString(), xiangyu_ganshou_text.getText()
							.toString());
					dbLawOper.update(db, "Xiangshi", xiangshi_time_text
							.getText().toString(), xiangshi_ganshou_text
							.getText().toString());
					dbLawOper.update(db, "Xiangzhi", xiangzhi_time_text
							.getText().toString(), xiangzhi_ganshou_text
							.getText().toString());
					dbLawOper.update(db, "Xindong", xindong_time_text.getText()
							.toString(), xindong_ganshou_text.getText()
							.toString());
					
					for (int i = 0; i < fileMode_list.size(); i++) {
						if (fileMode_list.get(i).getAliasfilename()
								.equals("你的表白")) {
							File oldFile = new File(fileMode_list.get(i)
									.getFilepath());
							File newFileVolce = new File(FilePath.USER_MIC_PATH
									+ "/biaobaiVolce" + i + ".amr");
							CopyFile.copyfile(oldFile, newFileVolce, true);
						} else if (fileMode_list.get(i).getAliasfilename()
								.equals("你的女神照片")) {

							File oldFile = new File(fileMode_list.get(i)
									.getFilepath());
							picHelper.compressPic(oldFile);
							File newFileImage = new File(
									FilePath.USER_IMAGE_PATH + "/biaobaiImage"
											+ i + ".jpg");
							CopyFile.copyfile(oldFile, newFileImage, true);
						}
					}
					Toast.makeText(WriteLove.this, "数据更新成功", Toast.LENGTH_LONG)
							.show();
					File alloldVolceFile = new File(FilePath.SAVE_MIC_PATH_TOSD);
					CopyFile.DeleteFile(alloldVolceFile);
					File alloldImageFile = new File(
							FilePath.SAVE_IMAGELOAD_CACHE_PATH);
					CopyFile.DeleteFile(alloldImageFile);
				}
			}
			break;
		case R.id.tongxunlu:
			Intent intent = new Intent();
			intent.setClass(WriteLove.this, ContextActivity.class);
			startActivityForResult(intent, 1);
			break;
		case R.id.xiangyu_time_layout:
			timeDialog(xiangyu_time_text, hour + "", min + "");
			break;
		case R.id.xiangshi_time_layout:
			timeDialog(xiangshi_time_text, hour + "", min + "");
			break;
		case R.id.xiangzhi_time_layout:
			timeDialog(xiangzhi_time_text, hour + "", min + "");
			break;
		case R.id.xindong_time_layout:
			timeDialog(xindong_time_text, hour + "", min + "");
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		/** 在界面返回的时候调用界面返回方法 */
		file_choose_and_show_view.onActivityResult(requestCode, resultCode,
				data);

		ContentResolver resolver = getContentResolver();
		/**
		 * 因为两种方式都用到了startActivityForResult方法， 这个方法执行完后都会执行onActivityResult方法，
		 * 所以为了区别到底选择了那个方式获取图片要进行判断，
		 * 这里的requestCode跟startActivityForResult里面第二个参数对应
		 */

		switch (requestCode) {
		case 1:
			if (data != null) {
				String num = data.getStringExtra("number");
				telphone.setText(num);
			}
			break;

		default:
			break;
		}

	}

	public static Bitmap getPicFromBytes(byte[] bytes,
			BitmapFactory.Options opts) {
		if (bytes != null)
			if (opts != null)
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
						opts);
			else
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return null;
	}

	public static byte[] readStream(InputStream inStream) throws Exception {
		byte[] buffer = new byte[1024];
		int len = -1;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inStream.close();
		return data;

	}

	private void stopPlay() {
		if (talkVoice.isPlay()) {
			talkVoice.stopPlaying();
		}
	}

	LayoutInflater inflater;
	View view;
	WheelMain wheelMain;
	int year, month, day, hour, min;

	/** 时间选择对话框 */
	public void timeDialog(final TextView textView, String shour, String smin) {

		int iHour = Integer.valueOf(shour);
		int iMin = Integer.valueOf(smin);

		view = inflater.inflate(R.layout.choose_dialog, null);
		ScreenInfo screenInfo = new ScreenInfo(WriteLove.this);
		wheelMain = new WheelMain(view, 1);
		wheelMain.screenheight = screenInfo.getHeight();
		wheelMain.initDateTimePicker(year, month, day, iHour, iMin);

		new AlertDialog.Builder(WriteLove.this).setTitle("选择时间").setView(view)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						textView.setText(wheelMain.getTime());
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub

					}
				}).show();

	}

	/** 得到年月日时分秒 */
	public void getCalendar() {
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
		min = calendar.get(Calendar.MINUTE);
	}

}
