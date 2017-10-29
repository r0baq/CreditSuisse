package org.mroczkarobert.creditsuisse.transport;

import org.mroczkarobert.creditsuisse.type.ErrorCode;

public class ErrorData {

	private ErrorCode errorCode;
	private String message;
	
	public ErrorData() {
	}
	
	public ErrorData(ErrorCode errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public ErrorData(ErrorCode nonWorkingDay, String message, Object... args) {
		this(nonWorkingDay, String.format(message, args));
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorData [errorCode=" + errorCode + ", message=" + message + "]";
	}
}
