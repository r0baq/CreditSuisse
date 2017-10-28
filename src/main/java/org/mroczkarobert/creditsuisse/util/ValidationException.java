package org.mroczkarobert.creditsuisse.util;

import org.mroczkarobert.creditsuisse.type.ErrorCode;

public class ValidationException extends Exception {

	private static final long serialVersionUID = -3841158318594246505L;
	
	private ErrorCode errorCode;

	public ValidationException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public ValidationException(ErrorCode errorCode, String message, Object... args) {
		super(String.format(message, args));
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
