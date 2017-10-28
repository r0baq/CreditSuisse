package org.mroczkarobert.creditsuisse.service.impl;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mroczkarobert.creditsuisse.service.CurrencyService;
import org.mroczkarobert.creditsuisse.transport.RatesDay;
import org.mroczkarobert.creditsuisse.util.InvalidCurrencyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyServiceRemoteImpl implements CurrencyService {

	private final static String URL = "http://api.fixer.io/{date}?base={currency}";
	
	private Logger log = LogManager.getLogger();
	private RestTemplate rest = new RestTemplate(); //RMR
	
	//RMR cache?
	public boolean isWorkingDay(LocalDate checkedDay, String currency) throws InvalidCurrencyException {
		log.info("Calling external API: {}, {}, {}", URL, checkedDay, currency);
		
		try {
			RatesDay dayData = rest.getForObject(URL, RatesDay.class, checkedDay, currency);
			log.info("Response from external API: {}", dayData);
			
			return checkedDay.equals(dayData.getDate());
			
		} catch (HttpClientErrorException exception) {
			log.warn(exception);
			
			if (HttpStatus.UNPROCESSABLE_ENTITY.equals(exception.getStatusCode())) {
				throw new InvalidCurrencyException();
				
			} else {
				throw exception;
			}
		}
	}
}
