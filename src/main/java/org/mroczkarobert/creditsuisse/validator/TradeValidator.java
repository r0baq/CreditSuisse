package org.mroczkarobert.creditsuisse.validator;

import org.mroczkarobert.creditsuisse.transport.ValidationResult;
import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.type.ProductType;

public interface TradeValidator {
	ValidationResult validate(Trade trade, ProductType productType);
}
