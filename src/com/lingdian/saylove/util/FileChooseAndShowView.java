package com.lingdian.saylove.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lingdian.saylove.R;
import com.lingdian.saylove.SysApp;
import com.lingdian.saylove.util.common.ChooseRemindDialog;
import com.lingdian.saylove.util.common.DialogUtils;
import com.lingdian.saylove.util.common.FilePath;
import com.lingdian.saylove.util.common.FileTypeIcon;
import com.lingdian.saylove.util.common.FileUtils;
import com.lingdian.saylove.util.common.ImageClickListener;
import com.lingdian.saylove.util.common.OptionsUtil;
import com.lingdian.saylove.util.common.TalkVoice;
import com.lingdian.saylove.util.common.TalkVoice.RecordCallback;
import com.lingdian.saylove.util.common.VoiceClickListener;
import com.lingdian.saylove.util.file.FileModels;
import com.lingdian.saylove.util.file.FileType;
import com.lingdian.saylove.util.file.SdFileModel;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 试用此View 需要 在代码初始化的时候调用 init 方法 并且 分别要在 对应的
 * Activity方法中调用onSubmitData()，onActivityResult
 * 
 * @author daijunyi
 * 
 */
public class FileChooseAndShowView extends RelativeLayout implements
		OnClickListener {
	private LayoutInflater layoutInflater;
	/** 图片 */
	private LinearLayout pic_layout;
	private ImageView create_iv_pic;
	/** 录音 */
	private LinearLayout record_layout;
	private ImageView create_iv_record;
	/** 视频 */
	private LinearLayout video_layout;
	private ImageView create_iv_video;
	/** 附件 */
	private LinearLayout file_layout;
	private ImageView create_iv_file;
	/** 媒体内容 */
	private LinearLayout create_lly_media_content;

	/** 末尾横线 */
	private View file_singline;

	private Activity activity;

	private TalkVoice talkVoice;

	private String PHOTO = "";
	private String video_file = "";
	/** 默认是能拍照的 */
	private boolean canTakepHotos = true;

	/** 附件列表 **/
	private List<FileModels> fileMode_list = null;

	private ImageLoader imageLoader = SysApp.getApp().getImageLoader();
	private FileTypeIcon iconUtil;

	/** 是否能拍照 */
	public boolean isCanTakepHotos() {
		return canTakepHotos;
	}

	/** 设置是否能拍照 */
	public void setCanTakepHotos(boolean canTakepHotos) {
		this.canTakepHotos = canTakepHotos;
	}

	public FileChooseAndShowView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		layoutInflater = LayoutInflater.from(context);
		initView();
	}

	public FileChooseAndShowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		layoutInflater = LayoutInflater.from(context);
		initView();
	}

	public FileChooseAndShowView(Context context) {
		super(context);
		layoutInflater = LayoutInflater.from(context);
		initView();
	}

	private void initView() {
		View inflate = layoutInflater.inflate(
				R.layout.oa_file_choose_and_show_view, this);
		create_lly_media_content = (LinearLayout) inflate
				.findViewById(R.id.create_lly_media_content);
		file_singline = (View) inflate.findViewById(R.id.file_singline);
		file_singline.setVisibility(View.GONE);
		create_iv_pic = (ImageView) inflate.findViewById(R.id.create_iv_pic);
		create_iv_record = (ImageView) inflate
				.findViewById(R.id.create_iv_record);
		create_iv_video = (ImageView) inflate
				.findViewById(R.id.create_iv_video);
		create_iv_file = (ImageView) inflate.findViewById(R.id.create_iv_file);

		pic_layout = (LinearLayout) inflate.findViewById(R.id.pic_layout);
		record_layout = (LinearLayout) inflate.findViewById(R.id.record_layout);
		video_layout = (LinearLayout) inflate.findViewById(R.id.video_layout);
		file_layout = (LinearLayout) inflate.findViewById(R.id.file_layout);

		pic_layout.setOnClickListener(this);
		record_layout.setOnClickListener(this);
		video_layout.setOnClickListener(this);
		file_layout.setOnClickListener(this);

		iconUtil = new FileTypeIcon();
	}

	/** 设置语音控件显示或隐藏 */
	public void setRecordGoneOrShow(int visibility) {
		create_iv_record.setVisibility(visibility);
	}

	/** 初始化 */
	public void init(Activity activity, TalkVoice talkVoice,
			List<FileModels> fileMode_list) {
		this.activity = activity;
		this.talkVoice = talkVoice;
		this.fileMode_list = fileMode_list;
	}

	/** 在 Acitivity 提交数据的方法中 调用此方法，初始化提交要初始化的数据 */
	public void onSubmitData() {
		submitData();
	}

	/** 在Activity onActivityResult方法中调用此方法 */
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		onResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pic_layout:
			if (canTakepHotos == true) {
				/** 照片 **/
				PHOTO = DialogUtils.showGetPicDialog(activity);
			} else {
				DialogUtils.showChoosePic(activity);
			}
			break;
		case R.id.record_layout:
			/** 语音 **/
			talkVoice.record_view(activity, new RecordCallback() {
				public void stopCallback(int i) {
					String path = talkVoice.stopRecording(i);
					if (path != null) {
						FileModels fileModeage = new FileModels();
						fileModeage.setFiletype(FileType.TYPE_VOICE);
						fileModeage.setFilepath(path);
						fileModeage.setExtensionname(".amr");
						fileModeage.setAliasfilename("你的表白");
						fileModeage.setVoiceTime(talkVoice.getRecordTime() + "");
						fileMode_list.add(fileModeage);
						setListfileModeView(fileMode_list);
					}
				}
			});
			break;
		}
	}

	private void submitData() {
		initListEditString();
	}

	private void onResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		/** 照片选择完成后 **/
		case DialogUtils.PHOTO_PICTURE:
			if (resultCode == activity.RESULT_OK && null != data) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };
				Cursor cursor = activity.getContentResolver().query(
						selectedImage, filePathColumn, null, null, null);
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				cursor.close();
				FileModels file = new FileModels();
				file.setFiletype(FileType.TYPE_IMAGE);
				file.setFilepath(picturePath);
				file.setThumbnailfilepath(picturePath);
				file.setExtensionname(".jpg");
				file.setAliasfilename("你的女神照片");
				fileMode_list.add(file);
				setListfileModeView(fileMode_list);
			}
			break;
		/** 拍照完成后 **/
		case DialogUtils.PHOTO_CAMERA:
			if (PHOTO != null && FileUtils.checkFilePathExists(PHOTO)) {
				FileModels file = new FileModels();
				file.setFiletype(FileType.TYPE_IMAGE);
				file.setFilepath(PHOTO);
				file.setThumbnailfilepath(PHOTO);
				file.setExtensionname(".jpg");
				file.setAliasfilename("你的女神照片");
				fileMode_list.add(file);
				setListfileModeView(fileMode_list);
			}

			break;
		/** 视频录制完成后 **/
		case DialogUtils.VIDEO_RECORD:
			if (resultCode == activity.RESULT_OK) {
				FileModels file = new FileModels();
				file.setFiletype(FileType.TYPE_VIDEO);
				file.setFilepath(video_file);
				file.setExtensionname(".3gp");
				file.setDescription("");
				fileMode_list.add(file);
				setListfileModeView(fileMode_list);
			}
			break;
		/** 附件选择完成后 **/
		case DialogUtils.SDFILE_RECORD:
			if (resultCode == activity.RESULT_OK && data != null) {
				SdFileModel sdfilemodel = (SdFileModel) data
						.getSerializableExtra("sdfilemodel");
				FileModels file = new FileModels();
				file.setFiletype(FileType.TYPE_OTHER);
				file.setFilepath(sdfilemodel.getFilepath());
				file.setExtensionname(sdfilemodel.getExtensionname());
				file.setDescription(sdfilemodel.getFilename());
				file.setAliasfilename(sdfilemodel.getFilename());
				fileMode_list.add(file);
				setListfileModeView(fileMode_list);
			}
			break;
		}
	}

	public void initListfileModeView(final List<FileModels> fileMode_list) {
		if (this.fileMode_list == null) {
			this.fileMode_list = new ArrayList<FileModels>();
		}
		if (this.fileMode_list != fileMode_list) {
			this.fileMode_list.clear();
			this.fileMode_list.addAll(fileMode_list);
		}
		setListfileModeView(this.fileMode_list);
	}

	/***
	 * 动态添加、显示文件列表（图片、语音、视频、附件）
	 * 
	 * @param fileMode_list
	 */
	private void setListfileModeView(final List<FileModels> fileMode_list) {
		initListEditString();
		create_lly_media_content.removeAllViews();
		for (int i = 0; i < fileMode_list.size(); i++) {
			// file_singline.setVisibility(View.VISIBLE);
			FileModels fileModeage = fileMode_list.get(i);
			View view = layoutInflater.inflate(
					R.layout.oa_task_create_file_item, null);
			RelativeLayout item_maintain_infoLI = (RelativeLayout) view
					.findViewById(R.id.item_maintain_infoLI);
			ImageView item_maintain_info_image = (ImageView) view
					.findViewById(R.id.item_maintain_info_image);
			EditText item_maintain_infoED = (EditText) view
					.findViewById(R.id.item_maintain_infoED);
			TextView item_voice_time = (TextView) view
					.findViewById(R.id.item_voice_time);
			TextView task_create_file_single_top = (TextView) view
					.findViewById(R.id.task_create_file_single_top);
			TextView task_create_file_single = (TextView) view
					.findViewById(R.id.task_create_file_single);
			ImageView madintain_info_detele = (ImageView) view
					.findViewById(R.id.madintain_info_detele);
			/** 设置删除按钮点击事件 **/
			madintain_info_detele.setTag(i);
			madintain_info_detele.setOnClickListener(new removeClickListener(
					fileMode_list));
			item_voice_time.setVisibility(View.GONE);

			/** 设置最后一个位置细线隐藏 **/
			if (i == (fileMode_list.size() - 1)) {
				task_create_file_single.setVisibility(View.GONE);
			}
			task_create_file_single_top.setVisibility(View.VISIBLE);
			item_maintain_infoED.setText(fileModeage.getDescription());
			if (FileType.TYPE_IMAGE.equals(fileModeage.getFiletype())) {
				item_maintain_infoED.setHint("请输入图片描述");
				item_maintain_infoLI
						.setBackgroundResource(R.drawable.oa_task_create_bg_top);
				item_maintain_info_image.setPadding(2, 2, 2, 2);
				if (fileModeage.isFromHttp() == true) {
					String filepath = fileModeage.getFilepath();
					String path = FilePath.SAVE_PHOTO_FILE_TOSD
							+ fileModeage.getFilekey()
							+ FilePath.SMALL_CAHE_FLAG;
					File file = new File(path);
					if (file.isFile() && file.exists()) {
						imageLoader.displayImage("file:///" + path,
								item_maintain_info_image,
								OptionsUtil.PicNormal());
					} else {
						imageLoader.displayImage(filepath,
								item_maintain_info_image,
								OptionsUtil.PicNormal());
					}
					item_maintain_info_image
							.setOnClickListener(new ImageClickListener(
									activity, fileModeage.getFilepath(),
									fileModeage.getFilekey()));
				} else {
					String thumbnailfilepath = fileModeage
							.getThumbnailfilepath();
					imageLoader.displayImage("file:///" + thumbnailfilepath,
							item_maintain_info_image, OptionsUtil.PicNormal());
					item_maintain_info_image
							.setOnClickListener(new ImageClickListener(
									activity, fileModeage.getFilepath()));
				}
			} else if (FileType.TYPE_VOICE.equals(fileModeage.getFiletype())) {
				item_maintain_infoED.setHint("请输入语音描述");
				item_voice_time.setVisibility(View.VISIBLE);
				item_voice_time.setText(fileModeage.getVoiceTime() + "'");
				if (fileModeage.isFromHttp() == true) {
					item_maintain_info_image
							.setOnClickListener(new VoiceClickListener(
									activity, talkVoice, fileModeage
											.getFilepath(), fileModeage
											.getFilekey(),
									item_maintain_info_image, item_voice_time,
									fileModeage.getVoiceTime(), "1"));
				} else {
					item_maintain_info_image
							.setOnClickListener(new VoiceClickListener(
									activity, talkVoice, fileModeage
											.getFilepath(),
									item_maintain_info_image, item_voice_time,
									fileModeage.getVoiceTime(), "1"));
				}
			}
			// else if (FileType.TYPE_OTHER.equals(fileModeage.getFiletype())
			// || FileType.TYPE_DOC.equals(fileModeage.getFiletype())) {
			// item_maintain_infoED.setHint("请输入文件描述");
			// item_maintain_info_image.setImageResource(FileTypeIcon
			// .getIconId(fileModeage.getFilepath()));
			// if (fileModeage.isFromHttp() == true) {
			// item_maintain_info_image
			// .setOnClickListener(new FileClickListener(activity,
			// fileModeage.getFilepath(), fileModeage
			// .getFilekey()));
			// } else {
			// String thumbnailfilepath = fileModeage.getFilepath();
			// item_maintain_info_image
			// .setOnClickListener(new FileClickListener(activity,
			// thumbnailfilepath));
			// }
			// } else if (FileType.TYPE_VIDEO.equals(fileModeage.getFiletype()))
			// {
			// item_maintain_infoED.setHint("请输入视频描述");
			// item_maintain_info_image
			// .setOnClickListener(new VideoPlayListener(activity,
			// fileModeage.getFilepath()));
			// item_maintain_info_image
			// .setImageResource(R.drawable.oa_btn_video_selecter);
			// }
			// 添加到布局文件
			create_lly_media_content.addView(view);
		}
	}

	/** 点击删除按钮，删除当条文件信息 **/
	private class removeClickListener implements View.OnClickListener {
		private List<FileModels> fileMode_list;

		public removeClickListener(List<FileModels> fileMode_list) {
			this.fileMode_list = fileMode_list;
		}

		@Override
		public void onClick(final View view) {
			ChooseRemindDialog delDlg = new ChooseRemindDialog(activity,
					R.style.oa_MyDialogStyleTop, "请确认", "确定删除该文件？", "确定", "取消") {
				@Override
				public void doBtn1Click() {
					this.dismiss();
					// 根据标记、删除文件item
					int i = (Integer) view.getTag();
					fileMode_list.remove(i);
					create_lly_media_content.removeViewAt(i);
					// 重新给文件item打标记
					for (int j = 0; j < create_lly_media_content
							.getChildCount(); j++) {
						ImageView item_maintain_info_image = (ImageView) create_lly_media_content
								.getChildAt(j).findViewById(
										R.id.madintain_info_detele);
						item_maintain_info_image.setTag(j);

					}
				}

				@Override
				public void doBtn2Click() {
					this.dismiss();
				}
			};
			delDlg.show();
		}
	}

	/** 初始化文件说明 **/
	private void initListEditString() {
		for (int i = 0; i < create_lly_media_content.getChildCount(); i++) {
			View childAt = create_lly_media_content.getChildAt(i);
			int y = (Integer) childAt.findViewById(R.id.madintain_info_detele)
					.getTag();
			fileMode_list.get(y)
					.setDescription(
							((EditText) childAt
									.findViewById(R.id.item_maintain_infoED))
									.getText().toString().trim());
		}
	}

}
