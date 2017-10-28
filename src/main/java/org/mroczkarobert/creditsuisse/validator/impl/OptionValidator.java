package org.mroczkarobert.creditsuisse.validator.impl;

import java.time.LocalDate;
import java.util.Set;

import org.mroczkarobert.creditsuisse.transport.ErrorTrade;
import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.type.ErrorCode;
import org.mroczkarobert.creditsuisse.type.ProductType;
import org.mroczkarobert.creditsuisse.validator.BaseValidator;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

@Service
public class OptionValidator extends BaseValidator {

	private static final String AMERICAN = "AMERICAN";
	private static final Set<String> VALID_STYLES = Sets.newHashSet("EUROPEAN", AMERICAN);
	
	public ErrorTrade validate(Trade trade, ProductType productType) {
		ErrorTrade result = super.validate(trade, productType);
		String style = trade.getStyle();
		
		if (!VALID_STYLES.contains(style)) { //RMR ignoreCase?
			result.add(ErrorCode.INVALID_STYLE, "Invalid style %s", style);
		}
		
		if (AMERICAN.equals(style)) { //RMR ignoreCase - jw
			LocalDate excerciseStartDate = trade.getExcerciseStartDate();
			LocalDate deliveryDate = trade.getDeliveryDate();
			
			if (excerciseStartDate == null) {
				result.add(ErrorCode.EMPTY_EXCERCISE_START_DATE, "%s style cannot have empty excercise start date", AMERICAN);
			}
			
			if (trade.getExpiryDate() == null) {
				result.add(ErrorCode.EMPTY_EXPIRY_DATE, "%s style cannot have empty expire date", AMERICAN);
			}
			
			if (trade.getPremiumDate() == null) {
				result.add(ErrorCode.EMPTY_PREMIUM_DATE, "%s style cannot have empty premium date", AMERICAN);
			}
			
			if (deliveryDate == null) {
				result.add(ErrorCode.EMPTY_DELIVERY_DATE, "%s style cannot have empty delivery date", AMERICAN);
			}
			
			if (isBefore(excerciseStartDate, trade.getTradeDate())) {
				result.add(ErrorCode.EXCERCISE_START_DATE_BEFORE_TRADE_DATE, "Excercise start date has to be after the trade date");
			}
			
			if (isAfter(excerciseStartDate, trade.getExpiryDate())) {
				result.add(ErrorCode.EXCERCISE_START_DATE_AFTER_EXPIRY_DATE, "Excercise start date has to be before the expiry date");
			}
			
			if (isAfter(trade.getExpiryDate(), deliveryDate)) {
				result.add(ErrorCode.EXPIRY_DATE_AFTER_DELIVERY_DATE, "Expiry date has to be before the delivery date");
			}
			
			if (isAfter(trade.getPremiumDate(), deliveryDate)) {
				result.add(ErrorCode.PREMIUM_DATE_AFTER_DELIVERY_DATE, "Premium date has to be before the delivery date");
			}
		}
		
		return result;
	}
}
