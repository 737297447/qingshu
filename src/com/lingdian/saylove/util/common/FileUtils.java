package com.lingdian.saylove.util.common;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import com.lingdian.saylove.util.file.SdFileModel;

/**
 * 文件操作工具包
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class FileUtils {
	/**
	 * 写文本文件 在Android系统中，文件保存在 /data/data/PACKAGE_NAME/files 目录下
	 * 
	 * @param context
	 * @param msg
	 */
	public static void write(Context context, String fileName, String content) {
		if (content == null)
			content = "";

		try {
			FileOutputStream fos = context.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			fos.write(content.getBytes());

			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取文本文件
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static String read(Context context, String fileName) {
		try {
			FileInputStream in = context.openFileInput(fileName);
			return readInStream(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String readInStream(FileInputStream inStream) {
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, length);
			}

			outStream.close();
			inStream.close();
			return outStream.toString();
		} catch (IOException e) {
			Log.i("FileTest", e.getMessage());
		}
		return null;
	}
	
	public static File createFile(String fileName) {
		File destDir = new File(fileName);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		if(!destDir.exists()){
			return null;
		}
		return destDir;
	}

	public static File createFile(String folderPath, String fileName) {
		File destDir = new File(folderPath);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		return new File(folderPath, fileName + fileName);
	}

	/**
	 * 向手机写图片
	 * 
	 * @param buffer
	 * @param folder
	 * @param fileName
	 * @return
	 */
	public static boolean writeFile(byte[] buffer, String folder,
			String fileName) {
		boolean writeSucc = false;

		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);

		String folderPath = "";
		if (sdCardExist) {
			folderPath = Environment.getExternalStorageDirectory()
					+ File.separator + folder + File.separator;
		} else {
			writeSucc = false;
		}

		File fileDir = new File(folderPath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		File file = new File(folderPath + fileName);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(buffer);
			writeSucc = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return writeSucc;
	}

	/**
	 * 根据文件绝对路径获取文件名
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		if (StringUtils.isEmpty(filePath))
			return "";
		return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
	}

	/**
	 * 根据文件的绝对路径获取文件名但不包含扩展名
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileNameNoFormat(String filePath) {
		if (StringUtils.isEmpty(filePath)) {
			return "";
		}

		int point = filePath.lastIndexOf('?');
		if(point != -1){
			String filepath = filePath.substring(0, point);
			filepath = filepath.substring(filepath.lastIndexOf(File.separator) + 1, point);
			int points = filepath.lastIndexOf('.');
			if (points != -1) {
				return filepath.substring(0, points);
			} else {
				return filepath;
			}
		} else {
			int points = filePath.lastIndexOf('.');
			if (points != -1) {
				return filePath.substring(filePath.lastIndexOf(File.separator) + 1, points);
			} else {
				return filePath.substring(filePath.lastIndexOf(File.separator) + 1, filePath.length());
			}
		}
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileFormat(String filePath) {
		if (StringUtils.isEmpty(filePath))
			return "";

		int point = filePath.lastIndexOf('?');
		if(point != -1){
			String filepath = filePath.substring(0, point);
			filepath = filepath.substring(filepath.lastIndexOf(File.separator) + 1, point);
			int points = filepath.lastIndexOf('.');
			if(points !=-1){
				return filepath.substring(points);
			} else {
				return "";
			}
		} else {
			int points = filePath.lastIndexOf('.');
			if(points !=-1){
				return filePath.substring(points);
			} else {
				return "";
			}
		}
	}

	/**
	 * 获取文件大小
	 * 
	 * @param filePath
	 * @return
	 */
	public static long getFileSize(String filePath) {
		long size = 0;

		File file = new File(filePath);
		if (file != null && file.exists()) {
			size = file.length();
		}
		return size;
	}

	/**
	 * 获取文件大小
	 * 
	 * @param size
	 *            字节
	 * @return
	 */
	public static String getFileSize(long size) {
		if (size <= 0)
			return "0";
		java.text.DecimalFormat df = new java.text.DecimalFormat("##.##");
		float temp = (float) size / 1024;
		if (temp >= 1024) {
			return df.format(temp / 1024) + "M";
		} else {
			return df.format(temp) + "K";
		}
	}

	/**
	 * 转换文件大小
	 * 
	 * @param fileS
	 * @return B/KB/MB/GB
	 */
	public static String formatFileSize(long fileS) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 获取目录文件大小
	 * 
	 * @param dir
	 * @return
	 */
	public static long getDirSize(File dir) {
		if (dir == null) {
			return 0;
		}
		if (!dir.isDirectory()) {
			return 0;
		}
		long dirSize = 0;
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				dirSize += file.length();
			} else if (file.isDirectory()) {
				dirSize += file.length();
				dirSize += getDirSize(file); // 递归调用继续统计
			}
		}
		return dirSize;
	}

	/**
	 * 获取目录文件个数
	 * 
	 * @param f
	 * @return
	 */
	public long getFileList(File dir) {
		long count = 0;
		File[] files = dir.listFiles();
		count = files.length;
		for (File file : files) {
			if (file.isDirectory()) {
				count = count + getFileList(file);// 递归
				count--;
			}
		}
		return count;
	}

	public static byte[] toBytes(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int ch;
		while ((ch = in.read()) != -1) {
			out.write(ch);
		}
		byte buffer[] = out.toByteArray();
		out.close();
		return buffer;
	}

	/**
	 * 检查文件是否存在
	 * 
	 * @param name
	 * @return
	 */
	public static boolean checkFileExists(String name) {
		boolean status;
		if (!name.equals("")) {
			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + name);
			status = newPath.exists();
		} else {
			status = false;
		}
		return status;
	}
	
    /**
     * 判断SD卡上文件是否存在
     * @param fileName
     * @return
*/
    public static boolean isFileExist(String fileName){
        File file=new File(fileName);
        return file.exists();
    }

    /**
     * 将一个inputStream里面的数据写到SD卡中
     * @param path
     * @param fileName
     * @param inputStream
     * @return
*/
    public static File  writeToSDfromInput(String path,String fileName,InputStream inputStream){
        //createSDDir(path);
    	String filepath = path+fileName; 
        File file = createFile(filepath);
        OutputStream outStream=null;
        try {
            outStream=new FileOutputStream(filepath);
            byte[] buffer=new byte[4*1024];
            while(inputStream.read(buffer)!=-1){
                outStream.write(buffer);
            }
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
	/**
	 * 检查路径是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static boolean checkFilePathExists(String path) {
		if(path ==null){
			return false;
		}
		return new File(path).exists();
	}

	/**
	 * 计算SD卡的剩余空间
	 * 
	 * @return 返回-1，说明没有安装sd卡
	 */
	public static long getFreeDiskSpace() {
		String status = Environment.getExternalStorageState();
		long freeSpace = 0;
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			try {
				File path = Environment.getExternalStorageDirectory();
				StatFs stat = new StatFs(path.getPath());
				long blockSize = stat.getBlockSize();
				long availableBlocks = stat.getAvailableBlocks();
				freeSpace = availableBlocks * blockSize / 1024;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return -1;
		}
		return (freeSpace);
	}

	/**
	 * 新建目录
	 * 
	 * @param directoryName
	 * @return
	 */
	public static boolean createDirectory(String directoryName) {
		boolean status;
		if (!directoryName.equals("")) {
			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + directoryName);
			status = newPath.mkdir();
			status = true;
		} else
			status = false;
		return status;
	}

	/**
	 * 检查是否安装SD卡
	 * 
	 * @return
	 */
	public static boolean checkSaveLocationExists() {
		String sDCardStatus = Environment.getExternalStorageState();
		boolean status;
		if (sDCardStatus.equals(Environment.MEDIA_MOUNTED)) {
			status = true;
		} else
			status = false;
		return status;
	}

	/**
	 * 删除目录(包括：目录里的所有文件)
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deleteDirectory(String fileName) {
		boolean status;
		SecurityManager checker = new SecurityManager();

		if (!fileName.equals("")) {

			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + fileName);
			checker.checkDelete(newPath.toString());
			if (newPath.isDirectory()) {
				String[] listfile = newPath.list();
				// delete all files within the specified directory and then
				// delete the directory
				try {
					for (int i = 0; i < listfile.length; i++) {
						File deletedFile = new File(newPath.toString() + "/"
								+ listfile[i].toString());
						deletedFile.delete();
					}
					newPath.delete();
					Log.i("DirectoryManager deleteDirectory", fileName);
					status = true;
				} catch (Exception e) {
					e.printStackTrace();
					status = false;
				}

			} else
				status = false;
		} else
			status = false;
		return status;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileName) {
		boolean status;
		SecurityManager checker = new SecurityManager();

		if (!fileName.equals("")) {

//			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(fileName);
			checker.checkDelete(newPath.toString());
			if (newPath.isFile()) {
				try {
					Log.i("DirectoryManager deleteFile", fileName);
					newPath.delete();
					status = true;
				} catch (SecurityException se) {
					se.printStackTrace();
					status = false;
				}
			} else
				status = false;
		} else
			status = false;
		return status;
	}

	/**
	 * 删除空目录
	 * 
	 * 返回 0代表成功 ,1 代表没有删除权限, 2代表不是空目录,3 代表未知错误
	 * 
	 * @return
	 */
	public static int deleteBlankPath(String path) {
		File f = new File(path);
		if (!f.canWrite()) {
			return 1;
		}
		if (f.list() != null && f.list().length > 0) {
			return 2;
		}
		if (f.delete()) {
			return 0;
		}
		return 3;
	}

	/**
	 * 重命名
	 * 
	 * @param oldName
	 * @param newName
	 * @return
	 */
	public static boolean reNamePath(String oldName, String newName) {
		File f = new File(oldName);
		return f.renameTo(new File(newName));
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 */
	public static boolean deleteFileWithPath(String filePath) {
		SecurityManager checker = new SecurityManager();
		File f = new File(filePath);
		checker.checkDelete(filePath);
		if (f.isFile()) {
			Log.i("DirectoryManager deleteFile", filePath);
			f.delete();
			return true;
		}
		return false;
	}

	/**
	 * 获取SD卡的根目录，末尾带\
	 * 
	 * @return
	 */
	public static String getSDRoot() {
		return Environment.getExternalStorageDirectory().getAbsolutePath()
				+ File.separator;
	}

	/**
	 * 列出路径下面的文件目录
	 * 
	 * @param path
	 * @return 路径下面的文件目录
	 */
	public static List<SdFileModel> listPath(String root) {
		List<SdFileModel> allDir = new ArrayList<SdFileModel>();
		SecurityManager checker = new SecurityManager();
		File path = new File(root);
		checker.checkRead(root);
		try {
			if (path.isDirectory()) {
				for (File f : path.listFiles()) {
					SdFileModel filemodel = new SdFileModel();
					if (f.isDirectory()) {
						filemodel.setIsFileDirectory(true);
						filemodel.setExtensionname("");
						filemodel.setFilesize("");
					} else {
						filemodel.setIsFileDirectory(false);
						filemodel.setExtensionname(getFileFormat(f.getName()));
						filemodel.setFilesize(formatFileSize(f.length()));
					}
					Date date = new Date(f.lastModified());
					String time = dateFormater.get().format(date);
					filemodel.setParentfilepath(f.getParent());
					filemodel.setFiletime(time);
					filemodel.setFilepath(f.getAbsolutePath());
					filemodel.setFilename(f.getName());
					allDir.add(filemodel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allDir;
	}
	
	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm");
		}
	};

	public enum PathStatus {
		SUCCESS, EXITS, ERROR
	}

	/**
	 * 创建目录
	 * 
	 * @param path
	 */
	public static PathStatus createPath(String newPath) {
		File path = new File(newPath);
		if (path.exists()) {
			return PathStatus.EXITS;
		}
		if (path.mkdir()) {
			return PathStatus.SUCCESS;
		} else {
			return PathStatus.ERROR;
		}
	}
	
	public static boolean iscreatePath(String filename) {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
			File recordFile = new File(filename);
			//创建文件夹
			recordFile.getParentFile().mkdirs(); 
				if(!recordFile.exists()) {
					try {
						return recordFile.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				return false;
		}
		return false;
	}

	/**
	 * 截取路径名
	 * 
	 * @return
	 */
	public static String getPathName(String absolutePath) {
		int start = absolutePath.lastIndexOf(File.separator) + 1;
		int end = absolutePath.length();
		return absolutePath.substring(start, end);
	}
	
	/**
	 * dip 转px
	 * px  转dip
	 * @return
	 */
	
	public static int dip2px(Context context, int dipValue){ 
        final float scale = context.getResources().getDisplayMetrics().density; 
        return (int)(dipValue * scale + 0.5f); 
	} 

	public static int px2dip(Context context, int pxValue){ 
        final float scale = context.getResources().getDisplayMetrics().density; 
        return (int)(pxValue / scale + 0.5f); 
	} 
	
	/**
	 * 根据文件后缀名获得对应的MIME类型。
	 * @param file
	 */ 
	public static String getMIMEType(File file) { 
		//{后缀名，MIME类型} 
		final String[][] MIME_MapTable={ 
				{".3gp",    "video/3gpp"}, 
				{".apk",    "application/vnd.android.package-archive"}, 
				{".asf",    "video/x-ms-asf"}, 
				{".avi",    "video/x-msvideo"}, 
				{".bin",    "application/octet-stream"}, 
				{".bmp",    "image/bmp"}, 
				{".c",  	"text/plain"}, 
				{".class",  "application/octet-stream"}, 
				{".conf",   "text/plain"}, 
				{".cpp",    "text/plain"}, 
				{".doc",    "application/msword"}, 
				{".docx",   "application/vnd.openxmlformats-officedocument.wordprocessingml.document"}, 
				{".xls",    "application/vnd.ms-excel"},  
				{".xlsx",   "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"}, 
				{".exe",    "application/octet-stream"}, 
				{".gif",    "image/gif"}, 
				{".gtar",   "application/x-gtar"}, 
				{".gz", 	"application/x-gzip"}, 
				{".h",  	"text/plain"}, 
				{".htm",    "text/html"}, 
				{".html",   "text/html"}, 
				{".jar",    "application/java-archive"}, 
				{".java",   "text/plain"}, 
				{".jpeg",   "image/jpeg"}, 
				{".jpg",    "image/jpeg"}, 
				{".js", 	"application/x-javascript"}, 
				{".log",    "text/plain"}, 
				{".m3u",    "audio/x-mpegurl"}, 
				{".m4a",    "audio/mp4a-latm"}, 
				{".m4b",    "audio/mp4a-latm"}, 
				{".m4p",    "audio/mp4a-latm"}, 
				{".m4u",    "video/vnd.mpegurl"}, 
				{".m4v",    "video/x-m4v"},  
				{".mov",    "video/quicktime"}, 
				{".mp2",    "audio/x-mpeg"}, 
				{".mp3",    "audio/x-mpeg"}, 
				{".mp4",    "video/mp4"}, 
				{".mpc",    "application/vnd.mpohun.certificate"},        
				{".mpe",    "video/mpeg"},   
				{".mpeg",   "video/mpeg"},   
				{".mpg",    "video/mpeg"},   
				{".mpg4",   "video/mp4"},    
				{".mpga",   "audio/mpeg"}, 
				{".msg",    "application/vnd.ms-outlook"}, 
				{".ogg",    "audio/ogg"}, 
				{".pdf",    "application/pdf"}, 
				{".png",    "image/png"}, 
				{".pps",    "application/vnd.ms-powerpoint"}, 
				{".ppt",    "application/vnd.ms-powerpoint"}, 
				{".pptx",   "application/vnd.openxmlformats-officedocument.presentationml.presentation"}, 
				{".prop",   "text/plain"}, 
				{".rc", 	"text/plain"}, 
				{".rmvb",   "audio/x-pn-realaudio"}, 
				{".rtf",    "application/rtf"}, 
				{".sh", 	"text/plain"}, 
				{".tar",    "application/x-tar"},    
				{".tgz",    "application/x-compressed"},  
				{".txt",    "text/plain"}, 
				{".wav",    "audio/x-wav"}, 
				{".wma",    "audio/x-ms-wma"}, 
				{".wmv",    "audio/x-ms-wmv"}, 
				{".wps",    "application/vnd.ms-works"}, 
				{".xml",    "text/plain"}, 
				{".z",  	"application/x-compress"}, 
				{".zip",    "application/x-zip-compressed"}, 
				{"",        "*/*"}   
		}; 

		String type="*/*"; 
		String end = getFileFormat(file.getName());
		if(end=="")return type; 
		for(int i=0;i<MIME_MapTable.length;i++){
			if(end.equals(MIME_MapTable[i][0])) 
				type = MIME_MapTable[i][1]; 
		} 
		return type; 
	} 
	
}