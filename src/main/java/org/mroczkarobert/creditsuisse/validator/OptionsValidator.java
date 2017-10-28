package org.mroczkarobert.creditsuisse.validator;

import java.time.LocalDate;
import java.util.Set;

import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.type.ErrorCode;
import org.mroczkarobert.creditsuisse.util.ValidationException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

@Service
public class OptionsValidator extends BaseValidator {

	private static final String AMERICAN = "AMERICAN";
	private static final Set<String> VALID_STYLES = Sets.newHashSet("EUROPEAN", AMERICAN);
	
	public void validate(Trade trade) throws ValidationException {
		super.validate(trade);
		String style = trade.getStyle();
		
		if (!VALID_STYLES.contains(style)) { //RMR ignoreCase?
			throw new ValidationException(ErrorCode.INVALID_STYLE, "Invalid style %s", style);
		}
		
		if (AMERICAN.equals(style)) { //RMR ignoreCase - jw
			LocalDate excerciseStartDate = trade.getExcerciseStartDate();
			LocalDate deliveryDate = trade.getDeliveryDate();
			
			if (excerciseStartDate == null) {
				throw new ValidationException(ErrorCode.EMPTY_EXCERCISE_START_DATE, "%s style cannot have empty excercise start date", AMERICAN);
			}
			
			if (trade.getExpiryDate() == null) {
				throw new ValidationException(ErrorCode.EMPTY_EXPIRY_DATE, "%s style cannot have empty expire date", AMERICAN);
			}
			
			if (trade.getPremiumDate() == null) {
				throw new ValidationException(ErrorCode.EMPTY_PREMIUM_DATE, "%s style cannot have empty premium date", AMERICAN);
			}
			
			if (deliveryDate == null) {
				throw new ValidationException(ErrorCode.EMPTY_DELIVERY_DATE, "%s style cannot have empty delivery date", AMERICAN);
			}
			
			if (!excerciseStartDate.isAfter(trade.getTradeDate())) {
				throw new ValidationException(
					ErrorCode.EXCERCISE_START_DATE_BEFORE_TRADE_DATE, "Excercise start date has to be after the trade date"
				);
			}
			
			if (!excerciseStartDate.isBefore(trade.getExpiryDate())) {
				throw new ValidationException(
					ErrorCode.EXCERCISE_START_DATE_AFTER_EXPIRY_DATE, "Excercise start date has to be before the expiry date"
				);
			}
			
			if (!trade.getExpiryDate().isBefore(deliveryDate)) {
				throw new ValidationException(
					ErrorCode.EXPIRY_DATE_AFTER_DELIVERY_DATE, "Expiry date has to be before the delivery date"
				);
			}
			
			if (!trade.getPremiumDate().isBefore(deliveryDate)) {
				throw new ValidationException(
					ErrorCode.PREMIUM_DATE_AFTER_DELIVERY_DATE, "Premium date has to be before the delivery date"
				);
			}
		}
	}
}
