package com.lingdian.saylove.util.file;


import java.io.Serializable;

public class ID implements Serializable{
	 /// <summary>
    /// Id
    /// </summary>
    private String Id;
    /// <summary>
    /// Êý¾Ý°æ±¾ºÅ
    /// </summary>
    private String Version;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
    
    
}

