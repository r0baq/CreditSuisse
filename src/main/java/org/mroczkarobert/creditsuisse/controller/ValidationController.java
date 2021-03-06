package org.mroczkarobert.creditsuisse.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mroczkarobert.creditsuisse.service.MetricsService;
import org.mroczkarobert.creditsuisse.service.ValidationService;
import org.mroczkarobert.creditsuisse.transport.ValidationResult;
import org.mroczkarobert.creditsuisse.transport.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/validate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ValidationController {

	private Logger log = LogManager.getLogger();
	
	@Autowired
	private ValidationService validationService;
	
	@Autowired
	private MetricsService metricsService;
	
	@RequestMapping
	public ResponseEntity<Collection<ValidationResult>> validate(@RequestBody Collection<Trade> trades) {
		long startTime = metricsService.start();
		log.info("Received request: {}", trades);
		
		Collection<ValidationResult> errors = new ArrayList<>(0);
		
		for (Trade trade : trades) {
			ValidationResult result = validationService.validate(trade);
			if (result.hasErrors()) {
				errors.add(result);
			}
		}
		
		HttpStatus responseStatus = (errors.isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
		
		log.info("Sending response: {}", errors);
		metricsService.end(startTime);
		return new ResponseEntity<>(errors, responseStatus);
	}
}
