package org.mroczkarobert.creditsuisse.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
public class ValidationController {

	@RequestMapping
	public void validate() {
		System.out.println("RMR01");
	}
}
