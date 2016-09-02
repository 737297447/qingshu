package com.lingdian.saylove.util.common;


import java.io.File;
import java.util.HashMap;

import android.text.TextUtils;
import android.widget.ImageView;

import com.lingdian.saylove.R;
/**
 * 根据文件后缀名  到map取图标资源ID
 * @author daijy
 */
public class FileTypeIcon {

	private static HashMap<String, Integer> fileIcon = new HashMap<String, Integer>();
	private static HashMap<String, String> fileIconName = new HashMap<String, String>();

	static {
		if (fileIcon.size() == 0) {
			initFileIcon();
		}
	}

	private static int defaultIcon = R.drawable.icon_other;

	/**
	 * @param v
	 * @param fileType
	 *            文件后缀
	 */
	public static void setImageResourceByFileType(ImageView v, String fileType) {
		setIcon(v, fileType);
	}

	public static void setImageResourceByFile(ImageView v, File f) {
		if (f == null) {
			throw new IllegalStateException("setImageResource @param File does not null");
		}
		setImageResourceByFileType(v, FileUtils.getFileFormat(f.getName()));
	}

	public static void setImageResourceByFileName(ImageView v, String fileName) {
		setImageResourceByFileType(v, FileUtils.getFileFormat(fileName));
	}

	public static void setImageResourceByFilePath(ImageView v, String filePath) {
		setImageResourceByFileType(v, FileUtils.getFileFormat(filePath));
	}

	/** 获取图标id */
	public static int getIconId(String extensionname) {
		if (TextUtils.isEmpty(extensionname)) {
			return defaultIcon;
		}
		Integer integer = fileIcon.get(FileUtils.getFileFormat(extensionname).trim().replace(".", "").toLowerCase());
		if (integer == null) {
			return defaultIcon;
		}
		return integer;
	}
	/** 获取图标名字 */
	public static String getIconName(String extensionname) {
		if (TextUtils.isEmpty(extensionname)) {
			return fileIconName.get("other");
		}
		String integer = fileIconName.get(FileUtils.getFileFormat(extensionname).trim().replace(".", "").toLowerCase());
		if (TextUtils.isEmpty(integer)) {
			return fileIconName.get("other");
		}
		return integer;
	}
	private static void setIcon(ImageView v, String fileType) {
		if (v == null) {
			throw new IllegalStateException("setImageResource @param ImageView does not null");
		}
		if (TextUtils.isEmpty(fileType)) {
			v.setImageResource(defaultIcon);
			return;
		}
		Integer integer = fileIcon.get(fileType.trim().replace(".", "").toLowerCase());
		if (integer == null) {
			v.setImageResource(defaultIcon);
		} else {
			v.setImageResource(integer);
		}
	}

	private static void initFileIcon() {
		fileIcon.put("aac", R.drawable.icon_aac);
		fileIcon.put("apk", R.drawable.icon_apk);
		fileIcon.put("avi", R.drawable.icon_avi);
		fileIcon.put("doc", R.drawable.icon_doc);
		fileIcon.put("docx", R.drawable.icon_doc);
		fileIcon.put("xls", R.drawable.icon_xls);
		fileIcon.put("xlsx", R.drawable.icon_xls);
		fileIcon.put("gif", R.drawable.icon_gif);
		fileIcon.put("jpg", R.drawable.icon_jpg);
		fileIcon.put("mp3", R.drawable.icon_mp3);
		fileIcon.put("mp4", R.drawable.icon_mp4);
		fileIcon.put("pdf", R.drawable.icon_pdf);
		fileIcon.put("png", R.drawable.icon_png);
		fileIcon.put("ppt", R.drawable.icon_ppt);
		fileIcon.put("pptx", R.drawable.icon_ppt);
		fileIcon.put("rtf", R.drawable.icon_rtf);
		fileIcon.put("txt", R.drawable.icon_txt);
		fileIcon.put("arm", R.drawable.icon_amr);
		fileIcon.put("zip", R.drawable.icon_zip);
		fileIcon.put("rar", R.drawable.icon_rar);
		fileIcon.put("exe", R.drawable.icon_exe);
		
		fileIconName.put("aac", "http://m.oaim.cn/Content/default/images/aac.png");
		fileIconName.put("apk", "http://m.oaim.cn/Content/default/images/apk.png");
		fileIconName.put("avi", "http://m.oaim.cn/Content/default/images/avi.png");
		fileIconName.put("doc", "http://m.oaim.cn/Content/default/images/doc.png");
		fileIconName.put("docx", "http://m.oaim.cn/Content/default/images/doc.png");
		fileIconName.put("xls", "http://m.oaim.cn/Content/default/images/xls.png");
		fileIconName.put("xlsx", "http://m.oaim.cn/Content/default/images/xls.png");
		fileIconName.put("gif", "http://m.oaim.cn/Content/default/images/gif.png");
		fileIconName.put("jpg", "http://m.oaim.cn/Content/default/images/jpg.png");
		fileIconName.put("mp3", "http://m.oaim.cn/Content/default/images/mp3.png");
		fileIconName.put("mp4", "http://m.oaim.cn/Content/default/images/mp4.png");
		fileIconName.put("pdf", "http://m.oaim.cn/Content/default/images/pdf.png");
		fileIconName.put("png", "http://m.oaim.cn/Content/default/images/png.png");
		fileIconName.put("ppt", "http://m.oaim.cn/Content/default/images/ppt.png");
		fileIconName.put("pptx", "http://m.oaim.cn/Content/default/images/ppt.png");
		fileIconName.put("rtf", "http://m.oaim.cn/Content/default/images/rtf.png");
		fileIconName.put("txt", "http://m.oaim.cn/Content/default/images/txt.png");
		fileIconName.put("arm", "http://m.oaim.cn/Content/default/images/amr.png");
		fileIconName.put("zip", "http://m.oaim.cn/Content/default/images/zip.png");
		fileIconName.put("rar", "http://m.oaim.cn/Content/default/images/rar.png");
		fileIconName.put("exe", "http://m.oaim.cn/Content/default/images/exe.png");
		fileIconName.put("other", "http://m.oaim.cn/Content/default/images/other.png");
	}
	
}
