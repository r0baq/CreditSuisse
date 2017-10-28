package org.mroczkarobert.creditsuisse.validator;

import org.mroczkarobert.creditsuisse.transport.ErrorTrade;
import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.type.ProductType;

public interface TradeValidator {

	ErrorTrade validate(Trade trade, ProductType productType);
}
