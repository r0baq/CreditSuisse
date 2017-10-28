package org.mroczkarobert.creditsuisse.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class TimeService {//RMR po co to?

	public LocalDate getCurrentDate() {
		return LocalDate.parse("2016-10-09");
	}
}
