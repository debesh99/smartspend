package com.debesh.smartspend.model;

public class ErrorOutputModel {
	private int errorCode;
	private String errorMessgae;

	public ErrorOutputModel(int errorCode, String errorMessgae) {
		this.errorCode = errorCode;
		this.errorMessgae = errorMessgae;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessgae() {
		return errorMessgae;
	}

	public void setErrorMessgae(String errorMessgae) {
		this.errorMessgae = errorMessgae;
	}
}