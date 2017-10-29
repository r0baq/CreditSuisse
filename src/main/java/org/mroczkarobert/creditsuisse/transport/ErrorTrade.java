package org.mroczkarobert.creditsuisse.transport;

import java.util.ArrayList;
import java.util.Collection;

import org.mroczkarobert.creditsuisse.type.ErrorCode;

public class ErrorTrade {

	private Trade trade;
	private Collection<ErrorData> errors = new ArrayList<>(0);
	
	public ErrorTrade() {
	}
	
	public ErrorTrade(Trade trade) {
		this.trade = trade;
	}
	
	public ErrorTrade(Trade trade, ErrorCode errorCode, String message) {
		this.trade = trade;
		add(errorCode, message);
	}

	public Trade getTrade() {
		return trade;
	}
	
	public Collection<ErrorData> getErrors() {
		return errors;
	}
	
	public void add(ErrorCode errorCode, String message) {
		errors.add(new ErrorData(errorCode, message));
	}

	public void addIfNotNull(ErrorData errorData) {
		if (errorData != null) {
			errors.add(errorData);
		}
	}

	public void add(ErrorCode errorCode, String message, Object... args) {
		errors.add(new ErrorData(errorCode, message, args));
	}
	
	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	@Override
	public String toString() {
		return "ErrorTrade [trade=" + trade + ", errors=" + errors + "]";
	}
}
