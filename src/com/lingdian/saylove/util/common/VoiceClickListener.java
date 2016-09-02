package com.lingdian.saylove.util.common;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingdian.saylove.R;

public class VoiceClickListener implements View.OnClickListener {
	private Activity activity;
	private TalkVoice voice;
	private ImageView view;
	private String filePath;
	private TextView time_te;
	private int time;
	private String type;
	/** 下载完要传到 */
	private String filePathKey;

	private String fileKey;

	public VoiceClickListener(Activity activity, TalkVoice voice,
			String filePath, ImageView view, TextView time_te, String time,
			String type) {
		this.activity = activity;
		this.voice = voice;
		this.view = view;
		this.filePath = filePath;
		this.time_te = time_te;
		try {
			this.time = Integer.parseInt(time);
		} catch (NumberFormatException e) {
			this.time = 1;
		}
		this.type = type;
	}

	public VoiceClickListener(Activity activity, TalkVoice voice,
			String filePath, String fileKey, ImageView view, TextView time_te,
			String time, String typer) {
		this.activity = activity;
		this.voice = voice;
		this.view = view;
		this.filePath = filePath;
		this.time_te = time_te;
		this.fileKey = fileKey;
		try {
			this.time = Integer.parseInt(time);
		} catch (NumberFormatException e) {
			this.time = 1;
		}
		this.type = type;
		filePathKey = FilePath.SAVE_MIC_PATH_TOSD + fileKey;
	}

	public void onClick(View v) {
		if (voice.isPlay()) {
			voice.stopPlaying();
			if ("1".equals(type)) {
				view.setImageResource(R.drawable.oa_selecter_media_record_item);
			} else {
				view.setImageResource(R.drawable.oa_task_create_tianjia_yuyin_off);
			}
			return;
		}

		if (filePath != null && !filePath.equals("")) {

			voice.startPlaying(filePath, view, time_te, time, type);
		} else {
			DialogUtils.showToast(activity, "语音不存在");
		}
	}

}