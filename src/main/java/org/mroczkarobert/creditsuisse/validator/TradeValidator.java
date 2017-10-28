package org.mroczkarobert.creditsuisse.validator;

import org.mroczkarobert.creditsuisse.transport.ErrorTrade;
import org.mroczkarobert.creditsuisse.transport.Trade;

public interface TradeValidator {

	ErrorTrade validate(Trade trade);
}
