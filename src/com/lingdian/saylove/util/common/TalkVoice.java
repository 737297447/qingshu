package com.lingdian.saylove.util.common;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingdian.saylove.R;

public class TalkVoice {

	private static String mFileName = null;
	private MediaRecorder mRecorder = null;
	private long startRecordTime;
	private TextView textview;
	private Handler handler;
	private SimpleDateFormat sdf;
	private Date date;	
	private int RecordTime;
	private Button recording_view_ok;
	private TextView recording_view_text;
	private AnimationDrawable rocketAnimation;


	
	public TalkVoice() {

	}

	public int getRecordTime() {
		return RecordTime;
	}

	public void setRecordTime(int recordTime) {
		RecordTime = recordTime;
	}

	public void record_view(Activity activity, final RecordCallback record) {
		final Dialog dialog = new Dialog(activity, R.style.oa_dialog);
		View recording_view = LayoutInflater.from(activity).inflate(R.layout.oa_recording_view, null);
		dialog.setContentView(recording_view);
		dialog.show();
		Button recording_view_cancel = (Button) recording_view
				.findViewById(R.id.recording_view_cancel);
		ImageView recording_view_micro = (ImageView) recording_view
				.findViewById(R.id.recording_view_micro);
		recording_view_micro.setBackgroundResource(R.anim.oa_record_play);
		rocketAnimation = (AnimationDrawable) recording_view_micro.getBackground();
		recording_view_ok = (Button) recording_view.findViewById(R.id.recording_view_ok);
		recording_view_text = (TextView) recording_view.findViewById(R.id.recording_view_text);
		recording_view_cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dialog.dismiss();
				if (rocketAnimation.isRunning()) {
					rocketAnimation.stop();
				}
				record.stopCallback(1);
			}
		});
		recording_view_ok.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (recording_view_ok.getText().equals("完成")) {
					dialog.dismiss();
					if (rocketAnimation.isRunning()) {
						rocketAnimation.stop();
					}
					record.stopCallback(2);
				} else if (recording_view_ok.getText().equals("开始")) {
					recording_view_ok.setText("完成");
					rocketAnimation.start();
					startRecording(recording_view_text);
				}
			}
		});
	}

	public interface RecordCallback {
		public void stopCallback(int i);
	}

	public void startRecording(TextView textview) {
		this.textview = textview;
		handler = new Handler();
		date = new Date();
		sdf = new SimpleDateFormat("mm:ss");
		mFileName = FilePath.SAVE_MIC_PATH_TOSD + StringUtils.getlongDate();
		boolean isOK = FileUtils.iscreatePath(mFileName);
		if (!isOK) {
			textview.setText("录音出现错误");
			return;
		}
		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
		mRecorder.setOutputFile(mFileName);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		try {
			mRecorder.prepare();
			startRecordTime = System.currentTimeMillis();
			mRecorder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		handler.postDelayed(runnable, 1000);
	}

	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			date.setTime(System.currentTimeMillis() - startRecordTime);
			if (mRecorder != null) {
				textview.setText(sdf.format(date));
				handler.postDelayed(this, 1000);
			}
		}
	};

	public String stopRecording(int i) {
		try {
			if (mRecorder != null) {
				mRecorder.stop();
				mRecorder.release();
				mRecorder = null;
			}
			if (handler != null) {
				handler.removeCallbacks(runnable);
				handler = null;
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		if (System.currentTimeMillis() - startRecordTime < 1000 || i == 1) {
			if (mFileName != null) {
				deletePath(mFileName);
			}
			return null;
		} else {
			setRecordTime((int) (System.currentTimeMillis() - startRecordTime) / 1000);
			return mFileName;
		}
	}

	private MediaPlayer mPlayer;
	private ImageView imageView;
	private TextView item_text;
	private int total_time;
	private String type = "1";
	private Handler handler1;
	
	public void startPalying(Context context ,String mFileName,int time){
		startPlaying(mFileName, new ImageView(context), new TextView(context), time, "");
	}

	public void startPlaying(String mFileName, ImageView view, TextView time_te, int time, String types) {
		stopPlaying();
		mPlayer = new MediaPlayer();
		imageView = view;
		item_text = time_te;
		total_time = time;
		type = types;
		handler1 = new Handler();
		try {
			if ("1".equals(type)) {
				imageView.setImageResource(R.drawable.oa_selecter_media_record_play_item);
			} else if ("2".equals(type)) {
				imageView.setImageResource(R.drawable.oa_task_create_tianjia_yuyin_on);
			}
			mPlayer.setDataSource(mFileName);
			mPlayer.prepare();
			mPlayer.start();
			startRecordTime = System.currentTimeMillis();
			handler1.postDelayed(runnable1, 1000);
		} catch (IOException e) {
			if ("1".equals(type)) {
				imageView.setImageResource(R.drawable.oa_selecter_media_record_play_item);
			} else if ("2".equals(type)) {
				imageView.setImageResource(R.drawable.oa_task_create_tianjia_yuyin_off);
			}
		}
		mPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer arg0) {
				if ("1".equals(type)) {
					imageView.setImageResource(R.drawable.oa_selecter_media_record_play_item);
				} else if ("2".equals(type)) {
					imageView.setImageResource(R.drawable.oa_task_create_tianjia_yuyin_off);
				}
				stopPlaying();
			}
		});
	}

	Runnable runnable1 = new Runnable() {
		@Override
		public void run() {

			int time = (int) (System.currentTimeMillis() - startRecordTime) / 1000;
			if (mRecorder != null || total_time - time > 0) {
				item_text.setText((total_time - time) + "'");
			} else if (total_time - time == 0) {
				item_text.setText(total_time + "'");
			}
			handler1.postDelayed(this, 1000);
		}
	};


	public Boolean isPlay() {
		if (mPlayer != null && mPlayer.isPlaying()) {
			return true;
		}
		return false;
	}

	public void stopPlaying() {
		if (mPlayer != null) {
			mPlayer.stop();
			mPlayer.release();
			mPlayer = null;
		}
		if (handler1 != null) {
			handler1.removeCallbacks(runnable1);
			handler1 = null;
			item_text.setText(total_time + "'");
			if ("1".equals(type)) {
				imageView.setImageResource(R.drawable.oa_inputbox_voice_press);
			} else if ("2".equals(type)) {
				imageView.setImageResource(R.drawable.oa_task_create_tianjia_yuyin_off);
			}
		}
	}

	public String getTimes() {
		int times = 0;
		if (mPlayer != null) {
			times = mPlayer.getDuration();
			times = times / 3600;
		}
		return times + "";
	}

	public void deletePath(String filename) {
		File recordFile = new File(filename);
		if (recordFile.exists()) {
			recordFile.delete();
		}
	}

}
