package org.mroczkarobert.creditsuisse.controller;

import java.util.Collection;

import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.util.ValidationException;
import org.mroczkarobert.creditsuisse.util.ValidatorFactory;
import org.mroczkarobert.creditsuisse.validator.TradeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/validate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ValidationController {

	@Autowired
	private ValidatorFactory factory;
	
	@RequestMapping
	public void validate(@RequestBody Collection<Trade> trades) throws ValidationException {
		System.out.println("RMR01 " + trades);
		
		TradeValidator validator;
		
		for (Trade trade : trades) {
			validator = factory.getValidator(trade.getType());
			validator.validate(trade);
		}
	}
}
