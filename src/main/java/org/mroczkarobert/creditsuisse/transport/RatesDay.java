package org.mroczkarobert.creditsuisse.transport;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import org.mroczkarobert.creditsuisse.util.LocalDateDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class RatesDay {
	
	private String base;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	private LocalDate date;
	private Map<String, BigDecimal> rates;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Map<String, BigDecimal> getRates() {
		return rates;
	}

	public void setRates(Map<String, BigDecimal> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "RatesDay [base=" + base + ", date=" + date + ", rates=" + rates + "]";
	}
}
