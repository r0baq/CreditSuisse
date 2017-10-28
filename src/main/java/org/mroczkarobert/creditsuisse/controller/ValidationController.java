package org.mroczkarobert.creditsuisse.controller;

import java.util.Collection;

import org.mroczkarobert.creditsuisse.service.ValidationService;
import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.util.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/validate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ValidationController {

	@Autowired
	private ValidationService validationService;
	
	@RequestMapping
	public void validate(@RequestBody Collection<Trade> tradeData) throws ValidationException {
		System.out.println("RMR01 " + tradeData);
		
		for (Trade trade : tradeData) {
			validationService.validate(trade);
		}
	}
}
