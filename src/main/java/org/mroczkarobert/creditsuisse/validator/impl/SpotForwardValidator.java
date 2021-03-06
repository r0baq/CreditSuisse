package org.mroczkarobert.creditsuisse.validator.impl;

import java.time.LocalDate;
import java.util.Map;

import org.mroczkarobert.creditsuisse.transport.ValidationResult;
import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.type.ErrorCode;
import org.mroczkarobert.creditsuisse.type.ProductType;
import org.mroczkarobert.creditsuisse.validator.BaseValidator;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;

@Service
public class SpotForwardValidator extends BaseValidator {

	private static final Map<ProductType, LocalDate> TYPES_DATES = ImmutableMap.of(
		ProductType.SPOT, LocalDate.parse("2011-01-01"),
		ProductType.FORWARD, LocalDate.parse("2010-02-03")
	);
	
	public ValidationResult validate(Trade trade, ProductType productType) {
		ValidationResult result = super.validate(trade, productType);
		
		if (TYPES_DATES.get(productType).isAfter(trade.getValueDate())) {
			result.add(
				ErrorCode.TYPE_NOT_AVAILABLE_FOR_DATE, 
				"Type %s is not avalible for date %s", trade.getType(), trade.getValueDate()
			);
		}
		
		return result;
	}
}
