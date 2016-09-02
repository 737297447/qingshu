package com.lingdian.saylove.util.file;

public class SdFileModel extends Base{
	
	private String filepath;
	private String filename;
	private String filetime;
	private String parentfilepath;
	private String extensionname;
	private String filesize;
	private Boolean isFileDirectory;
	private String dirId;
	
	public String getDirId() {
		return dirId;
	}
	public void setDirId(String dirId) {
		this.dirId = dirId;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Boolean getIsFileDirectory() {
		return isFileDirectory;
	}
	public void setIsFileDirectory(Boolean isFileDirectory) {
		this.isFileDirectory = isFileDirectory;
	}
	public String getParentfilepath() {
		return parentfilepath;
	}
	public void setParentfilepath(String parentfilepath) {
		this.parentfilepath = parentfilepath;
	}
	public String getFiletime() {
		return filetime;
	}
	public void setFiletime(String filetime) {
		this.filetime = filetime;
	}
	public String getExtensionname() {
		return extensionname;
	}
	public void setExtensionname(String extensionname) {
		this.extensionname = extensionname;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	
}
