package org.mroczkarobert.creditsuisse.validator;

import java.time.LocalDate;
import java.util.Set;

import org.mroczkarobert.creditsuisse.service.CurrencyService;
import org.mroczkarobert.creditsuisse.transport.ErrorData;
import org.mroczkarobert.creditsuisse.transport.ErrorTrade;
import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.type.ErrorCode;
import org.mroczkarobert.creditsuisse.type.ProductType;
import org.mroczkarobert.creditsuisse.util.InvalidCurrencyException;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Sets;

public abstract class BaseValidator implements TradeValidator {
	
	private static final Set<String> VALID_CUSTOMERS = Sets.newHashSet("PLUTO1", "PLUTO2");
	private static final Set<String> VALID_LEGAL_ENTITIES = Sets.newHashSet("CS Zurich");
	
	@Autowired
	private CurrencyService currencyService;

	public ErrorTrade validate(Trade trade, ProductType productType) {
		ErrorTrade result = new ErrorTrade(trade);
		
		LocalDate valueDate = trade.getValueDate();
		LocalDate tradeDate = trade.getTradeDate();
		String currency1 = trade.getCcyPair().substring(0, 3);
		String currency2 = trade.getCcyPair().substring(3, 6);
		
		if (valueDate != null) {
			result.addIfNotNull(validateCurrencyAndWorkingDay(valueDate, currency1, ErrorCode.INVALID_CURRENCY_1, ErrorCode.NON_WORKING_DAY_1));
			result.addIfNotNull(validateCurrencyAndWorkingDay(valueDate, currency2, ErrorCode.INVALID_CURRENCY_2, ErrorCode.NON_WORKING_DAY_2));
		}
		
		if (tradeDate == null) {
			result.add(ErrorCode.EMPTY_TRADE_DATE, "Trade date cannot cannot be empty");
		}
		
		if (isBefore(valueDate, tradeDate)) {
			result.add(ErrorCode.VALUE_DATE_BEFORE_TRADE_DATE, "Value date cannot be before trade date");
		}
		
		if (!VALID_CUSTOMERS.contains(trade.getCustomer())) {
			result.add(ErrorCode.UNKNOWN_CUSTOMER, "Customer %s is not supported", trade.getCustomer());
		}
		
		if (!VALID_LEGAL_ENTITIES.contains(trade.getLegalEntity())) {
			result.add(ErrorCode.UNKNOWN_LEGAL_ENTITY, "Legal entity %s is not supported", trade.getLegalEntity());
		}
		
		return result;
	}
	
	protected boolean isBefore(LocalDate first, LocalDate second) {
		return first != null && second != null && first.isBefore(second);
	}
	
	protected boolean isAfter(LocalDate first, LocalDate second) {
		return first != null && second != null && first.isAfter(second);
	}

	private ErrorData validateCurrencyAndWorkingDay(LocalDate valueDate, String currency, ErrorCode invalidCurrency, ErrorCode nonWorkingDay) {
		try {
			if (!currencyService.isWorkingDay(valueDate, currency)) {
				return new ErrorData(nonWorkingDay, "Value date cannot fall on weekend or non-working day for currency %s", currency);
			}
			
		} catch (InvalidCurrencyException exception) {
			System.out.println("RMR " + exception);
			return new ErrorData(invalidCurrency, "Currency %s is invalid", currency);
		}
		return null;
	}
}
