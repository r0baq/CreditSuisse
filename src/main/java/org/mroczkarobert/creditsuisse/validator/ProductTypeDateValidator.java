package org.mroczkarobert.creditsuisse.validator;

import java.time.LocalDate;
import java.util.Map;

import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.type.ErrorCode;
import org.mroczkarobert.creditsuisse.type.ProductType;
import org.mroczkarobert.creditsuisse.util.ValidationException;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;

@Service
public class ProductTypeDateValidator extends BaseValidator {

	private static final Map<ProductType, LocalDate> TYPES_DATES = ImmutableMap.of(
		ProductType.SPOT, LocalDate.parse("2011-01-01"),
		ProductType.FORWARD, LocalDate.parse("2010-02-03")
	);
	
	public void validate(Trade trade) throws ValidationException {
		super.validate(trade);
		
		if (TYPES_DATES.get(trade.getType()).isAfter(trade.getValueDate())) {
			throw new ValidationException(
				ErrorCode.TYPE_NOT_AVAILABLE_FOR_DATE, 
				"Type %s is not avalible for date %s", trade.getType(), trade.getValueDate()
			);
		}
	}
}
