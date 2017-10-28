package org.mroczkarobert.creditsuisse.service;

import org.mroczkarobert.creditsuisse.transport.ErrorTrade;
import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.type.ErrorCode;
import org.mroczkarobert.creditsuisse.type.ProductType;
import org.mroczkarobert.creditsuisse.validator.TradeValidator;
import org.mroczkarobert.creditsuisse.validator.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

	@Autowired
	private ValidatorFactory factory;
	
	public ErrorTrade validate(Trade trade) {
		ProductType productType = ProductType.parse(trade.getType());
		TradeValidator validator = factory.getValidator(productType);
		
		if (validator == null) {
			return new ErrorTrade(trade, ErrorCode.UNKNOWN_PRODUCT_TYPE, "Unknown product type");
			
		} else {
			return validator.validate(trade, productType);
		}
	}
}
