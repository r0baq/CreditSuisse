package org.mroczkarobert.creditsuisse.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mroczkarobert.creditsuisse.service.ValidationService;
import org.mroczkarobert.creditsuisse.transport.ValidationResult;
import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.type.ErrorCode;
import org.mroczkarobert.creditsuisse.type.ProductType;
import org.mroczkarobert.creditsuisse.validator.TradeValidator;
import org.mroczkarobert.creditsuisse.validator.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

	private Logger log = LogManager.getLogger();
	
	@Autowired
	private ValidatorFactory factory;
	
	public ValidationResult validate(Trade trade) {
		log.info("Validating trade: {}", trade);
		
		ProductType productType = ProductType.parse(trade.getType());
		TradeValidator validator = factory.getValidator(productType);
		ValidationResult result;
		
		if (validator == null) {
			result = new ValidationResult(trade, ErrorCode.UNKNOWN_PRODUCT_TYPE, "Unknown product type");
			
		} else {
			result = validator.validate(trade, productType);
		}
		
		if (result.hasErrors()) {
			log.warn("Trade has errors: {}", result);
			
		} else {
			log.info("Trade validated without errors: {}", trade);
		}
		return result;
	}
}
