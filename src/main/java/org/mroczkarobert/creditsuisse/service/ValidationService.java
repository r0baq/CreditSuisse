package org.mroczkarobert.creditsuisse.service;

import java.time.LocalDate;

import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.type.ErrorCode;
import org.mroczkarobert.creditsuisse.util.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {
	
	@Autowired
	private WorkingDayService workingDayService;

	public void validate(Trade trade) throws ValidationException {
		
		LocalDate valueDate = trade.getValueDate();
		String currency1 = trade.getCcyPair().substring(0, 3);
		String currency2 = trade.getCcyPair().substring(3, 6);
		
		//RMR null
		if (valueDate.isBefore(trade.getTradeDate())) {
			throw new ValidationException(ErrorCode.VALUE_DATE_BEFORE_TRADE_DATE, "Value date cannot be before trade date"); //RMR opis do enuma
		}
		
		validateWorkingDay(valueDate, currency1, ErrorCode.NON_WORKING_DAY_1);
		validateWorkingDay(valueDate, currency2, ErrorCode.NON_WORKING_DAY_2);
	}

	private void validateWorkingDay(LocalDate valueDate, String currency, ErrorCode errorCode) throws ValidationException {
		if (!workingDayService.isWorkingDay(valueDate, currency)) {
			throw new ValidationException(errorCode, "Value date cannot fall on weekend or non-working day for currency %s", currency);
		}
	}
}
