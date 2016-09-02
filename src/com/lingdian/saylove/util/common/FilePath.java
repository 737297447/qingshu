package com.lingdian.saylove.util.common;

import java.io.File;

import android.os.Environment;

public class FilePath {
	
	// ����
	public final static File SDCARD = Environment.getExternalStorageDirectory();
	/** �ļ������� */
	public final static String FOLDER_NAME = "ILove";
	/** �������� */
	public final static String SAVE_MIC_PATH_TOSD = SDCARD + File.separator + FOLDER_NAME + "/voiceDown" + File.separator;
	/** ͼƬ�����ַ */
	public final static String SAVE_IMAGELOAD_CACHE_PATH = SDCARD + File.separator + FOLDER_NAME + "/imagecache" + File.separator;
	
	/** ���������ĵ�ַ */
	public final static String USER_MIC_PATH = SDCARD + File.separator + FOLDER_NAME + "/Voice" + File.separator;
	/** ����ͼƬ�ĵ�ַ */
	public final static String USER_IMAGE_PATH = SDCARD + File.separator + FOLDER_NAME + "/Image" + File.separator;
	
	
	
	/** ͼƬ���� */
	public final static String SAVE_PHOTO_FILE_TOSD = SDCARD + File.separator + "Android/data/com.lingdian.saylove/cache" + File.separator;
	/** Сͼ���� */
	public final static String SMALL_CAHE_FLAG = "small";
	public static final File sdCard = android.os.Environment.getExternalStorageDirectory();
	/**û��sd��������ͼƬ��ַ*/
	public final static String CHAT_IMAGE_PATH_NOSDCARD = File.separator + FOLDER_NAME + "/chat/image" + File.separator;
	
}
