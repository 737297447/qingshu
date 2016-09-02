package com.lingdian.saylove.util.file;

import java.io.Serializable;

/**
 */
public abstract class Base implements Serializable {

	public static final String ERROR_CODE = "error_code";
	public static final String ERROR_MSG = "error_msg";
	public static final String STATUS = "status";
	
	private String error_code;
	private String error_msg;
	
	private String errorCode = "-1";
	
	protected boolean isSuccess = true;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
