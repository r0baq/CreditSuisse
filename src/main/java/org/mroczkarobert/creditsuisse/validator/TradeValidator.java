package org.mroczkarobert.creditsuisse.validator;

import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.util.ValidationException;

public interface TradeValidator {

	void validate(Trade trade) throws ValidationException;
}
