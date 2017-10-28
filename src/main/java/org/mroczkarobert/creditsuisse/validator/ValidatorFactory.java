package org.mroczkarobert.creditsuisse.validator;

import org.mroczkarobert.creditsuisse.type.ProductType;
import org.mroczkarobert.creditsuisse.validator.impl.OptionValidator;
import org.mroczkarobert.creditsuisse.validator.impl.SpotForwardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidatorFactory {

	@Autowired
	private SpotForwardValidator spotForwardValidator;
	
	@Autowired
	private OptionValidator optionsValidator;
	
	public TradeValidator getValidator(ProductType productType) {
		if (ProductType.SPOT.equals(productType) || ProductType.FORWARD.equals(productType)) {
			return spotForwardValidator;
			
		} else if (ProductType.OPTION.equals(productType)) {
			return optionsValidator;
			
		} else {
			return null;
		}
	}
}
