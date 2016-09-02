package com.lingdian.saylove.util.file;



public class FileModels extends Base {
	// 是否网络数据
	private boolean isFromHttp = false;
	// modelId
	private String fileid ="";
	// model类型
	private String filetype ="";
	// model key 一般用于本地保存的文件名
	private String filekey ="";
	// model 的网络路径
	private String filepath ="";
	// 名字
	private String aliasfilename="" ;
	// 缩略图地址
	private String thumbnailfilepath="";
	// 扩展名
	private String extensionname="";
	// 文件大小
	private String filelength="";
	// 文件描述
	private String description="";
	// 文件版本
	private String version="";
	// 图片宽度
	private String imagewidth="";
	// 图片高度
	private String imageheight="";
	// 录音大小
	private String voicelength="";
	// 视频大小
	private String videolength="";
	// 录音时间长度
	private String voiceTime="";
	//  进度
	private String progress = "";
	// 
	private String md5 = "";
	
	private String localpath = "";
	
	public String getLocalpath() {
		return localpath;
	}

	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public boolean isFromHttp() {
		return isFromHttp;
	}

	public void setFromHttp(boolean isFromHttp) {
		this.isFromHttp = isFromHttp;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getFilekey() {
		return filekey;
	}

	public void setFilekey(String filekey) {
		this.filekey = filekey;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getAliasfilename() {
		return aliasfilename;
	}

	public void setAliasfilename(String aliasfilename) {
		this.aliasfilename = aliasfilename;
	}

	public String getThumbnailfilepath() {
		return thumbnailfilepath;
	}

	public void setThumbnailfilepath(String thumbnailfilepath) {
		this.thumbnailfilepath = thumbnailfilepath;
	}

	public String getVoiceTime() {
		return voiceTime;
	}

	public void setVoiceTime(String voiceTime) {
		this.voiceTime = voiceTime;
	}

	public String getExtensionname() {
		return extensionname;
	}

	public void setExtensionname(String extensionname) {
		this.extensionname = extensionname;
	}

	public String getFilelength() {
		return filelength;
	}

	public void setFilelength(String filelength) {
		this.filelength = filelength;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getImagewidth() {
		return imagewidth;
	}

	public void setImagewidth(String imagewidth) {
		this.imagewidth = imagewidth;
	}

	public String getImageheight() {
		return imageheight;
	}

	public void setImageheight(String imageheight) {
		this.imageheight = imageheight;
	}

	public String getVoicelength() {
		return voicelength;
	}

	public void setVoicelength(String voicelength) {
		this.voicelength = voicelength;
	}

	public String getVideolength() {
		return videolength;
	}

	public void setVideolength(String videolength) {
		this.videolength = videolength;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

}
