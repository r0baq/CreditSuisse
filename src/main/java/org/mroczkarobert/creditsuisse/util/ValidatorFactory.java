package org.mroczkarobert.creditsuisse.util;

import org.mroczkarobert.creditsuisse.type.ErrorCode;
import org.mroczkarobert.creditsuisse.type.ProductType;
import org.mroczkarobert.creditsuisse.validator.OptionsValidator;
import org.mroczkarobert.creditsuisse.validator.ProductTypeDateValidator;
import org.mroczkarobert.creditsuisse.validator.TradeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidatorFactory {

	@Autowired
	private ProductTypeDateValidator productTypeDateValidator;
	
	@Autowired
	private OptionsValidator optionsValidator;
	
	public TradeValidator getValidator(ProductType productType) throws ValidationException {
		if (ProductType.SPOT.equals(productType) || ProductType.FORWARD.equals(productType)) {
			return productTypeDateValidator;
			
		} else if (ProductType.OPTION.equals(productType)) {
			return optionsValidator;
			
		} else {
			throw new ValidationException(ErrorCode.UNKNOWN_PRODUCT_TYPE, "Unknown product type");
		}
	}
}
