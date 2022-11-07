package com.wecare.exception;

public class ErrorInfo {
	private String msg;
	private int errorcode;
	public ErrorInfo() {
	
	}
	public ErrorInfo(String msg, int errorcode) {
		this.msg = msg;
		this.errorcode = errorcode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}
	@Override
	public String toString() {
		return "ErrorInfo [msg=" + msg + ", errorcode=" + errorcode + "]";
	}
	
	
}
