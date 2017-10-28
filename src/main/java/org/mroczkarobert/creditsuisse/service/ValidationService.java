package org.mroczkarobert.creditsuisse.service;

import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.type.ErrorCode;
import org.mroczkarobert.creditsuisse.util.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

	public void validate(Trade trade) {
		
		//RMR null
		if (trade.getValueDate().isBefore(trade.getTradeDate())) {
			throw new ValidationException(ErrorCode.VALUE_DATE_BEFORE_TRADE_DATE, "Value date cannot be before trade date");
		}
		
	}
}
