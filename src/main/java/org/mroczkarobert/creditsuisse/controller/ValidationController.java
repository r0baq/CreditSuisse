package org.mroczkarobert.creditsuisse.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.mroczkarobert.creditsuisse.service.ValidationService;
import org.mroczkarobert.creditsuisse.transport.ErrorTrade;
import org.mroczkarobert.creditsuisse.transport.Trade;
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
	public Collection<ErrorTrade> validate(@RequestBody Collection<Trade> trades) {
		System.out.println("RMR01 " + trades);
		
		Collection<ErrorTrade> errors = new ArrayList<>(0);
		
		for (Trade trade : trades) {
			ErrorTrade result = validationService.validate(trade);
			if (!result.getErrors().isEmpty()) {
				errors.add(result);
			}
		}
		
		//RMR inny HttpStatusCode?
		
		return errors;
	}
}
