package org.mroczkarobert.creditsuisse.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mroczkarobert.creditsuisse.transport.ErrorTrade;
import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.util.InvalidCurrencyException;
import org.mroczkarobert.creditsuisse.validator.SpotForwardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestValidationService {

	@Mock
	private CurrencyRemoteService currencyRemoteService;
	
	@InjectMocks
	@Autowired
	private SpotForwardValidator spotForwardValidator;
	
	@Autowired
	private ValidationService validationService;
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldEndWithoutErrors() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("Spot");
		trade.setDirection("BUY");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setValueDate(LocalDate.parse("2016-08-15"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		Mockito.when(currencyRemoteService.isWorkingDay(LocalDate.parse("2016-08-15"), "EUR")).thenReturn(true);
		Mockito.when(currencyRemoteService.isWorkingDay(LocalDate.parse("2016-08-15"), "USD")).thenReturn(true);
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors()).isEmpty();
	}
}
