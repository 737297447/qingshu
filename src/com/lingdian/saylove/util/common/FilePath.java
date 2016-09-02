package com.lingdian.saylove.util.common;

import java.io.File;

import android.os.Environment;

public class FilePath {
	
	// 任务
	public final static File SDCARD = Environment.getExternalStorageDirectory();
	/** 文件夹名字 */
	public final static String FOLDER_NAME = "ILove";
	/** 语音缓存 */
	public final static String SAVE_MIC_PATH_TOSD = SDCARD + File.separator + FOLDER_NAME + "/voiceDown" + File.separator;
	/** 图片缓存地址 */
	public final static String SAVE_IMAGELOAD_CACHE_PATH = SDCARD + File.separator + FOLDER_NAME + "/imagecache" + File.separator;
	
	/** 保存语音的地址 */
	public final static String USER_MIC_PATH = SDCARD + File.separator + FOLDER_NAME + "/Voice" + File.separator;
	/** 保存图片的地址 */
	public final static String USER_IMAGE_PATH = SDCARD + File.separator + FOLDER_NAME + "/Image" + File.separator;
	
	
	
	/** 图片缓存 */
	public final static String SAVE_PHOTO_FILE_TOSD = SDCARD + File.separator + "Android/data/com.lingdian.saylove/cache" + File.separator;
	/** 小图编码 */
	public final static String SMALL_CAHE_FLAG = "small";
	public static final File sdCard = android.os.Environment.getExternalStorageDirectory();
	/**没有sd卡的聊天图片地址*/
	public final static String CHAT_IMAGE_PATH_NOSDCARD = File.separator + FOLDER_NAME + "/chat/image" + File.separator;
	
}
