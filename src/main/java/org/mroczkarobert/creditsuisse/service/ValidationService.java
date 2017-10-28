package org.mroczkarobert.creditsuisse.service;

import org.mroczkarobert.creditsuisse.transport.ErrorTrade;
import org.mroczkarobert.creditsuisse.transport.Trade;

public interface ValidationService {
	ErrorTrade validate(Trade trade);
}
