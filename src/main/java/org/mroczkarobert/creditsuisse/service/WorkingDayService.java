package org.mroczkarobert.creditsuisse.service;

import java.time.LocalDate;

import org.mroczkarobert.creditsuisse.transport.RatesDay;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WorkingDayService {

	private final static String BASE_URL = "http://api.fixer.io/";
	
	private RestTemplate restTemplate = new RestTemplate(); //RMR
	
	//RMR cache?
	//RMR currency enum?
	public boolean isWorkingDay(LocalDate checkedDay, String currency) {
		RatesDay dayData = restTemplate.getForObject(BASE_URL + "{date}?base={currency}", RatesDay.class, checkedDay, currency);
		System.out.println("RMR02" + dayData);
		
		return checkedDay.equals(dayData.getDate());
	}
}
