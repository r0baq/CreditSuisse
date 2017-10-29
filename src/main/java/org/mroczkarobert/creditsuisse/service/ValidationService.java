package org.mroczkarobert.creditsuisse.service;

import org.mroczkarobert.creditsuisse.transport.ValidationResult;
import org.mroczkarobert.creditsuisse.transport.Trade;

public interface ValidationService {
	ValidationResult validate(Trade trade);
}
