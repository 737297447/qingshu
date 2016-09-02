package com.lingdian.saylove.tools;

import java.io.File;
import java.io.FileOutputStream;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class CopyFile {

	public static void copyfile(File fromFile, File toFile, Boolean rewrite) {

		if (!fromFile.exists()) {
			return;
		}
		if (!fromFile.isFile()) {
			return;
		}
		if (!fromFile.canRead()) {
			return;
		}
		if (!toFile.getParentFile().exists()) {
			toFile.getParentFile().mkdirs();
		}
		if (toFile.exists() && rewrite) {
			toFile.delete();
		}
		try {
			java.io.FileInputStream fosfrom = new java.io.FileInputStream(
					fromFile);
			java.io.FileOutputStream fosto = new FileOutputStream(toFile);
			byte bt[] = new byte[1024];
			int c;
			while ((c = fosfrom.read(bt)) > 0) {
				fosto.write(bt, 0, c); // ������д�����ļ�����
			}
			fosfrom.close();
			fosto.close();
		} catch (Exception ex) {
			Log.e("readfile", ex.getMessage());
		}
	}

	/**
	 * �ݹ�ɾ���ļ����ļ���
	 * 
	 * @param file
	 *            Ҫɾ���ĸ�Ŀ¼
	 */
	public static void DeleteFile(File file) {
		if (file.exists() == false) {
			mHandler.sendEmptyMessage(0);
			return;
		} else {
			if (file.isFile()) {
				file.delete();
				return;
			}
			if (file.isDirectory()) {
				File[] childFile = file.listFiles();
				if (childFile == null || childFile.length == 0) {
					file.delete();
					return;
				}
				for (File f : childFile) {
					DeleteFile(f);
				}
				file.delete();
				mHandler.sendEmptyMessage(1);
			}
		}
	}

	static Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				System.out.println("�ļ����ļ��в�����");
				break;
			case 1:
				System.out.println("ɾ���ɹ���");
				break;
			default:
				break;
			}
		};
	};

}
