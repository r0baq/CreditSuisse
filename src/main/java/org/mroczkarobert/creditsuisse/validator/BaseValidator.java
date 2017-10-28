package org.mroczkarobert.creditsuisse.validator;

import java.time.LocalDate;
import java.util.Set;

import org.mroczkarobert.creditsuisse.service.CurrencyRemoteService;
import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.type.ErrorCode;
import org.mroczkarobert.creditsuisse.util.InvalidCurrencyException;
import org.mroczkarobert.creditsuisse.util.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

public abstract class BaseValidator implements TradeValidator {
	
	private static final Set<String> VALID_CUSTOMERS = Sets.newHashSet("PLUTO1", "PLUTO2");
	
	@Autowired
	private CurrencyRemoteService currencyRemoteService;

	public void validate(Trade trade) throws ValidationException {
		
		LocalDate valueDate = trade.getValueDate();
		String currency1 = trade.getCcyPair().substring(0, 3);
		String currency2 = trade.getCcyPair().substring(3, 6);
		
		if (valueDate != null) {
			if (valueDate.isBefore(trade.getTradeDate())) {
				throw new ValidationException(ErrorCode.VALUE_DATE_BEFORE_TRADE_DATE, "Value date cannot be before trade date"); //RMR opis do enuma
			}
			
			validateCurrencyAndWorkingDay(valueDate, currency1, ErrorCode.INVALID_CURRENCY_1, ErrorCode.NON_WORKING_DAY_1);
			validateCurrencyAndWorkingDay(valueDate, currency2, ErrorCode.INVALID_CURRENCY_2, ErrorCode.NON_WORKING_DAY_2);
		}
		
		if (trade.getTradeDate() == null) {
			throw new ValidationException(ErrorCode.EMPTY_TRADE_DATE, "Trade date cannot cannot be empty");
		}
		
		if (!VALID_CUSTOMERS.contains(trade.getCustomer())) {
			throw new ValidationException(ErrorCode.CUSTOMER_NOT_SUPPORTED, "Customer %s is not supported", trade.getCustomer());
		}
	}

	private void validateCurrencyAndWorkingDay(LocalDate valueDate, String currency, ErrorCode invalidCurrency, ErrorCode nonWorkingDay) throws ValidationException {
		try {
			if (!currencyRemoteService.isWorkingDay(valueDate, currency)) {
				throw new ValidationException(nonWorkingDay, "Value date cannot fall on weekend or non-working day for currency %s", currency);
			}
			
		} catch (InvalidCurrencyException exception) {
			System.out.println("RMR " + exception);
			throw new ValidationException(invalidCurrency, "Currency %s is invalid", currency);
		}
	}
}
