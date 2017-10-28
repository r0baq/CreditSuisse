package org.mroczkarobert.creditsuisse.service;

import java.time.LocalDate;

import org.mroczkarobert.creditsuisse.transport.RatesDay;
import org.mroczkarobert.creditsuisse.util.InvalidCurrencyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyRemoteService {

	private final static String URL = "http://api.fixer.io/{date}?base={currency}";
	
	private RestTemplate rest = new RestTemplate(); //RMR
	
	//RMR cache?
	//RMR currency enum?
	public boolean isWorkingDay(LocalDate checkedDay, String currency) throws InvalidCurrencyException {
		
		try {
			RatesDay dayData = rest.getForObject(URL, RatesDay.class, checkedDay, currency);
			System.out.println("RMR02" + dayData);
			
			return checkedDay.equals(dayData.getDate());
			
		} catch (HttpClientErrorException exception) {
			//RMR log exception
			
			if (HttpStatus.UNPROCESSABLE_ENTITY.equals(exception.getStatusCode())) {
				throw new InvalidCurrencyException();
				
			} else {
				throw exception;
			}
		}
	}
}
