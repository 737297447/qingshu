package com.lingdian.saylove.util.common;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.lingdian.saylove.R;

public class ProgressDlg extends ProgressDialog {

	private Context context;
	private Handler handle;

	public ProgressDlg(Context context) {

		this(context, "Loading", "Please wait......");
	}

	public ProgressDlg(Context context, String title, String message) {
		super(context);
		this.context = context;
		this.setCancelable(true);
		this.setTitle(title);
		this.setMessage(message);
		this.setIcon(R.drawable.oa_c_ico_info);
		this.setCanceledOnTouchOutside(false);
		this.setButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int i) {
				// 点击“确定按钮”取消对话框
				dialog.cancel();
			}
		});
		this.setOnDismissListener(new OnDismissListener() {
			public void onDismiss(DialogInterface dialog) {

				dialog.dismiss();
			}
		});

	}

	public ProgressDlg(Context context, String message) {
		super(context);
		this.context = context;
		this.setCancelable(true);
		this.setMessage(message);
		this.setIcon(R.drawable.oa_c_ico_info);
		this.setCancelable(false);
		this.setCanceledOnTouchOutside(false);
		this.setButton("确认", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int i) {

				dialog.cancel();
				dialog.dismiss();
			}
		});
	}

	public ProgressDlg(final Dialog preDialog) {

		this(preDialog, "Loading", "Please wait......");
	}

	public ProgressDlg(final Dialog preDialog, String title, String message) {
		super(preDialog.getContext());
		this.setCancelable(true);
		this.setTitle(title);
		this.setMessage(message);
		this.setIcon(R.drawable.oa_c_ico_info);
		this.setCanceledOnTouchOutside(false);
		this.setButton("确认", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int i) {

				dialog.cancel();
				preDialog.show();
			}
		});
		this.setOnDismissListener(new OnDismissListener() {
			public void onDismiss(DialogInterface dialog) {
				dialog.dismiss();

			}
		});

	}

	public ProgressDlg(Context context, String title, String message, Handler h) {
		super(context);
		this.context = context;
		this.handle = h;
		this.setCancelable(true);
		this.setTitle(title);
		this.setMessage(message);
		this.setIcon(R.drawable.oa_c_ico_info);
		this.setCanceledOnTouchOutside(false);
		this.setButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int i) {
				Message msg = new Message();
				msg.arg1 = 15;
				if (handle != null) {
					handle.sendMessage(msg);
				}
				// 点击“确定按钮”取消对话框
				dialog.cancel();
			}
		});
		this.setOnDismissListener(new OnDismissListener() {
			public void onDismiss(DialogInterface dialog) {
				Message msg = new Message();
				msg.arg1 = 15;
				if (handle != null) {
					handle.sendMessage(msg);
				}
				dialog.dismiss();

			}
		});

	}

}
