package com.lingdian.saylove.util.file;



public class FileModels extends Base {
	// �Ƿ���������
	private boolean isFromHttp = false;
	// modelId
	private String fileid ="";
	// model����
	private String filetype ="";
	// model key һ�����ڱ��ر�����ļ���
	private String filekey ="";
	// model ������·��
	private String filepath ="";
	// ����
	private String aliasfilename="" ;
	// ����ͼ��ַ
	private String thumbnailfilepath="";
	// ��չ��
	private String extensionname="";
	// �ļ���С
	private String filelength="";
	// �ļ�����
	private String description="";
	// �ļ��汾
	private String version="";
	// ͼƬ���
	private String imagewidth="";
	// ͼƬ�߶�
	private String imageheight="";
	// ¼����С
	private String voicelength="";
	// ��Ƶ��С
	private String videolength="";
	// ¼��ʱ�䳤��
	private String voiceTime="";
	//  ����
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
