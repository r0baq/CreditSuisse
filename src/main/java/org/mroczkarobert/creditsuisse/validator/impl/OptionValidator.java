package org.mroczkarobert.creditsuisse.validator.impl;

import java.time.LocalDate;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.mroczkarobert.creditsuisse.transport.ValidationResult;
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
	
	public ValidationResult validate(Trade trade, ProductType productType) {
		ValidationResult result = super.validate(trade, productType);
		String style = trade.getStyle();
		String payCurrency = trade.getPayCcy();
		String premiumCurrency = trade.getPremiumCcy();
		
		if (!VALID_STYLES.contains(style)) {
			result.add(ErrorCode.INVALID_STYLE, "Invalid style %s", style);
		}
		
		if (StringUtils.isNotBlank(payCurrency) && !currencyService.isValidCurrency(payCurrency)) {
			result.add(ErrorCode.INVALID_PAY_CURRENCY, "Currency %s is not a valid ISO 4217 currency", payCurrency);
		}
		
		if (StringUtils.isNotBlank(premiumCurrency) && !currencyService.isValidCurrency(premiumCurrency)) {
			result.add(ErrorCode.INVALID_PREMIUM_CURRENCY, "Currency %s is not a valid ISO 4217 currency", premiumCurrency);
		}
		
		if (AMERICAN.equals(style)) {
			LocalDate excerciseStartDate = trade.getExcerciseStartDate();
			LocalDate deliveryDate = trade.getDeliveryDate();
			LocalDate expiryDate = trade.getExpiryDate();
			
			if (excerciseStartDate == null) {
				result.add(ErrorCode.EMPTY_EXCERCISE_START_DATE, "%s style cannot have empty excercise start date", AMERICAN);
			}
			
			if (expiryDate == null) {
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
			
			if (isAfter(excerciseStartDate, expiryDate)) {
				result.add(ErrorCode.EXCERCISE_START_DATE_AFTER_EXPIRY_DATE, "Excercise start date has to be before the expiry date");
			}
			
			if (isAfter(expiryDate, deliveryDate)) {
				result.add(ErrorCode.EXPIRY_DATE_AFTER_DELIVERY_DATE, "Expiry date has to be before the delivery date");
			}
			
			if (isAfter(trade.getPremiumDate(), deliveryDate)) {
				result.add(ErrorCode.PREMIUM_DATE_AFTER_DELIVERY_DATE, "Premium date has to be before the delivery date");
			}
		}
		
		return result;
	}
}
