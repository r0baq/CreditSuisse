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
import org.mroczkarobert.creditsuisse.type.ErrorCode;
import org.mroczkarobert.creditsuisse.util.InvalidCurrencyException;
import org.mroczkarobert.creditsuisse.validator.impl.SpotForwardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestValidationServiceSpot {

	@Mock
	private WorkingDayService workingDayService;
	
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
	public void shouldSpotEndWithoutErrors() throws InvalidCurrencyException {
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
		
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "EUR")).thenReturn(true);
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "USD")).thenReturn(true);
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors()).isEmpty();
	}
	
	@Test
	public void shouldReportCurrencyNotSupportedInExternalSystem() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("XXXUSD");
		trade.setType("Spot");
		trade.setDirection("BUY");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setValueDate(LocalDate.parse("2016-08-15"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "XXX")).thenThrow(new InvalidCurrencyException());
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "USD")).thenReturn(true);
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.CURRENCY_NOT_SUPPORTED_IN_EXTERNAL_SYSTEM_1);
	}
	
	@Test
	public void shouldReportInvalidCurrencyPair() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EUR&USD");
		trade.setType("Spot");
		trade.setDirection("BUY");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setValueDate(LocalDate.parse("2016-08-15"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "EUR")).thenReturn(true);
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "USD")).thenReturn(true);
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.INVALID_CURRENCY_PAIR);
	}
	
	@Test
	public void shouldReportInvalidPairCurrency1() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("BLEUSD");
		trade.setType("Spot");
		trade.setDirection("BUY");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setValueDate(LocalDate.parse("2016-08-15"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "BLE")).thenThrow(new InvalidCurrencyException());
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "USD")).thenReturn(true);
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.INVALID_PAIR_CURRENCY_1);
	}
	
	@Test
	public void shouldReportInvalidPairCurrency2() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURBLE");
		trade.setType("Spot");
		trade.setDirection("BUY");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setValueDate(LocalDate.parse("2016-08-15"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "EUR")).thenReturn(true);
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "BLE")).thenThrow(new InvalidCurrencyException());
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.INVALID_PAIR_CURRENCY_2);
	}
	
	@Test
	public void shouldReportEmptyTradeDate() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("Spot");
		trade.setDirection("BUY");
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setValueDate(LocalDate.parse("2016-08-15"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "EUR")).thenReturn(true);
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "USD")).thenReturn(true);
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.EMPTY_TRADE_DATE);
	}
	
	@Test
	public void shouldReportValueDateBeforeTradeDate() throws InvalidCurrencyException {
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
		trade.setValueDate(LocalDate.parse("2016-08-10"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-10"), "EUR")).thenReturn(true);
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-10"), "USD")).thenReturn(true);
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.VALUE_DATE_BEFORE_TRADE_DATE);
	}
	
	@Test
	public void shouldReportUnknownCustomer() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO3");
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
		
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "EUR")).thenReturn(true);
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "USD")).thenReturn(true);
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.UNKNOWN_CUSTOMER);
	}
	
	@Test
	public void shouldReportUnkownLegalEntity() throws InvalidCurrencyException {
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
		trade.setLegalEntity("CS Zurich XXX");
		trade.setTrader("Johann Baumfiddler");
		
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "EUR")).thenReturn(true);
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2016-08-15"), "USD")).thenReturn(true);
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.UNKNOWN_LEGAL_ENTITY);
	}
	
	@Test
	public void shouldReportTypeNotAvalibleForDate() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("Spot");
		trade.setDirection("BUY");
		trade.setTradeDate(LocalDate.parse("2010-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setValueDate(LocalDate.parse("2010-12-31"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2010-12-31"), "EUR")).thenReturn(true);
		Mockito.when(workingDayService.isWorkingDay(LocalDate.parse("2010-12-31"), "USD")).thenReturn(true);
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.TYPE_NOT_AVAILABLE_FOR_DATE);
	}
	
	@Test
	public void shouldReportUnknownProductType() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("Other");
		trade.setDirection("BUY");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setValueDate(LocalDate.parse("2016-08-15"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.UNKNOWN_PRODUCT_TYPE);
	}
}