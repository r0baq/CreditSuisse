package org.mroczkarobert.creditsuisse.type;

public enum ErrorCode {
	EMPTY_TRADE_DATE,
	VALUE_DATE_BEFORE_TRADE_DATE,
	NON_WORKING_DAY_1,
	NON_WORKING_DAY_2,
	CURRENCY_NOT_SUPPORTED_IN_EXTERNAL_SYSTEM_1,
	CURRENCY_NOT_SUPPORTED_IN_EXTERNAL_SYSTEM_2,
	TYPE_NOT_AVAILABLE_FOR_DATE,
	UNKNOWN_PRODUCT_TYPE,
	UNKNOWN_CUSTOMER,
	UNKNOWN_LEGAL_ENTITY,
	INVALID_STYLE,
	EMPTY_EXCERCISE_START_DATE,
	EXCERCISE_START_DATE_BEFORE_TRADE_DATE,
	EXCERCISE_START_DATE_AFTER_EXPIRY_DATE, 
	EMPTY_EXPIRY_DATE, 
	EMPTY_PREMIUM_DATE, 
	EMPTY_DELIVERY_DATE, 
	EXPIRY_DATE_AFTER_DELIVERY_DATE, 
	PREMIUM_DATE_AFTER_DELIVERY_DATE, 
	INVALID_CURRENCY_PAIR, 
	INVALID_PAIR_CURRENCY_1, 
	INVALID_PAIR_CURRENCY_2, 
	INVALID_PAY_CURRENCY, 
	INVALID_PREMIUM_CURRENCY;
}
