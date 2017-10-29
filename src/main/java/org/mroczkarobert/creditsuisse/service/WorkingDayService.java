package org.mroczkarobert.creditsuisse.service;

import java.time.LocalDate;

import org.mroczkarobert.creditsuisse.util.InvalidCurrencyException;

public interface WorkingDayService {
	boolean isWorkingDay(LocalDate checkedDay, String currency) throws InvalidCurrencyException;
}
