package org.mroczkarobert.creditsuisse.util;

import org.mroczkarobert.creditsuisse.type.ErrorCode;

public class ValidationException extends javax.validation.ValidationException {

	private static final long serialVersionUID = -7822403504610767254L;
	
	private ErrorCode errorCode;

	public ValidationException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
