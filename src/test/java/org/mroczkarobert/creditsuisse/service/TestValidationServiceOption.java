package org.mroczkarobert.creditsuisse.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mroczkarobert.creditsuisse.transport.ErrorTrade;
import org.mroczkarobert.creditsuisse.transport.Trade;
import org.mroczkarobert.creditsuisse.type.ErrorCode;
import org.mroczkarobert.creditsuisse.util.InvalidCurrencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestValidationServiceOption {
	
	@Autowired
	private ValidationService validationService;
	
	@Test
	public void shouldOptionEuropeanEndWithoutErrors() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("VanillaOption");
		trade.setStyle("EUROPEAN");
		trade.setDirection("BUY");
		trade.setStrategy("CALL");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setDeliveryDate(LocalDate.parse("2016-08-22"));
		trade.setExpiryDate(LocalDate.parse("2016-08-19"));
		trade.setPayCcy("USD");
		trade.setPremium(new BigDecimal("0.20"));
		trade.setPremiumCcy("USD");
		trade.setPremiumType("%USD");
		trade.setPremiumDate(LocalDate.parse("2016-08-12"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors()).isEmpty();
	}
	
	@Test
	public void shouldReportInvalidStyle() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("VanillaOption");
		trade.setStyle("POLISH");
		trade.setDirection("BUY");
		trade.setStrategy("CALL");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setDeliveryDate(LocalDate.parse("2016-08-22"));
		trade.setExpiryDate(LocalDate.parse("2016-08-19"));
		trade.setPayCcy("USD");
		trade.setPremium(new BigDecimal("0.20"));
		trade.setPremiumCcy("USD");
		trade.setPremiumType("%USD");
		trade.setPremiumDate(LocalDate.parse("2016-08-12"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.INVALID_STYLE);
	}
	
	@Test
	public void shouldOptionAmericanEndWithoutErrors() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("VanillaOption");
		trade.setStyle("AMERICAN");
		trade.setDirection("BUY");
		trade.setStrategy("CALL");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setDeliveryDate(LocalDate.parse("2016-08-22"));
		trade.setExpiryDate(LocalDate.parse("2016-08-19"));
		trade.setExcerciseStartDate(LocalDate.parse("2016-08-12"));
		trade.setPayCcy("USD");
		trade.setPremium(new BigDecimal("0.20"));
		trade.setPremiumCcy("USD");
		trade.setPremiumType("%USD");
		trade.setPremiumDate(LocalDate.parse("2016-08-12"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors()).isEmpty();
	}
	
	@Test
	public void shouldReportEmptyExcerciseStartDate() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("VanillaOption");
		trade.setStyle("AMERICAN");
		trade.setDirection("BUY");
		trade.setStrategy("CALL");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setDeliveryDate(LocalDate.parse("2016-08-22"));
		trade.setExpiryDate(LocalDate.parse("2016-08-19"));
		trade.setPayCcy("USD");
		trade.setPremium(new BigDecimal("0.20"));
		trade.setPremiumCcy("USD");
		trade.setPremiumType("%USD");
		trade.setPremiumDate(LocalDate.parse("2016-08-12"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.EMPTY_EXCERCISE_START_DATE);
	}
	
	@Test
	public void shouldReportEmptyExpiryDate() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("VanillaOption");
		trade.setStyle("AMERICAN");
		trade.setDirection("BUY");
		trade.setStrategy("CALL");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setDeliveryDate(LocalDate.parse("2016-08-22"));
		trade.setExcerciseStartDate(LocalDate.parse("2016-08-12"));
		trade.setPayCcy("USD");
		trade.setPremium(new BigDecimal("0.20"));
		trade.setPremiumCcy("USD");
		trade.setPremiumType("%USD");
		trade.setPremiumDate(LocalDate.parse("2016-08-12"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.EMPTY_EXPIRY_DATE);
	}
	
	@Test
	public void shouldReportEmptyPremiumDate() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("VanillaOption");
		trade.setStyle("AMERICAN");
		trade.setDirection("BUY");
		trade.setStrategy("CALL");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setDeliveryDate(LocalDate.parse("2016-08-22"));
		trade.setExpiryDate(LocalDate.parse("2016-08-19"));
		trade.setExcerciseStartDate(LocalDate.parse("2016-08-12"));
		trade.setPayCcy("USD");
		trade.setPremium(new BigDecimal("0.20"));
		trade.setPremiumCcy("USD");
		trade.setPremiumType("%USD");
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.EMPTY_PREMIUM_DATE);
	}
	
	@Test
	public void shouldReportEmptyDeliveryDate() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("VanillaOption");
		trade.setStyle("AMERICAN");
		trade.setDirection("BUY");
		trade.setStrategy("CALL");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setExpiryDate(LocalDate.parse("2016-08-19"));
		trade.setExcerciseStartDate(LocalDate.parse("2016-08-12"));
		trade.setPayCcy("USD");
		trade.setPremium(new BigDecimal("0.20"));
		trade.setPremiumCcy("USD");
		trade.setPremiumType("%USD");
		trade.setPremiumDate(LocalDate.parse("2016-08-12"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.EMPTY_DELIVERY_DATE);
	}
	
	@Test
	public void shouldReportExcerciseStartDateBeforeTradeDate() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("VanillaOption");
		trade.setStyle("AMERICAN");
		trade.setDirection("BUY");
		trade.setStrategy("CALL");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setDeliveryDate(LocalDate.parse("2016-08-22"));
		trade.setExpiryDate(LocalDate.parse("2016-08-19"));
		trade.setExcerciseStartDate(LocalDate.parse("2016-08-10"));
		trade.setPayCcy("USD");
		trade.setPremium(new BigDecimal("0.20"));
		trade.setPremiumCcy("USD");
		trade.setPremiumType("%USD");
		trade.setPremiumDate(LocalDate.parse("2016-08-12"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.EXCERCISE_START_DATE_BEFORE_TRADE_DATE);
	}
	
	@Test
	public void shouldReportExcerciseStartDateAfterExpiryDate() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("VanillaOption");
		trade.setStyle("AMERICAN");
		trade.setDirection("BUY");
		trade.setStrategy("CALL");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setDeliveryDate(LocalDate.parse("2016-08-22"));
		trade.setExpiryDate(LocalDate.parse("2016-08-19"));
		trade.setExcerciseStartDate(LocalDate.parse("2016-08-20"));
		trade.setPayCcy("USD");
		trade.setPremium(new BigDecimal("0.20"));
		trade.setPremiumCcy("USD");
		trade.setPremiumType("%USD");
		trade.setPremiumDate(LocalDate.parse("2016-08-12"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.EXCERCISE_START_DATE_AFTER_EXPIRY_DATE);
	}
	
	@Test
	public void shouldReportExpiryDateAfterDeliveryDate() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("VanillaOption");
		trade.setStyle("AMERICAN");
		trade.setDirection("BUY");
		trade.setStrategy("CALL");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setDeliveryDate(LocalDate.parse("2016-08-22"));
		trade.setExpiryDate(LocalDate.parse("2016-08-23"));
		trade.setExcerciseStartDate(LocalDate.parse("2016-08-12"));
		trade.setPayCcy("USD");
		trade.setPremium(new BigDecimal("0.20"));
		trade.setPremiumCcy("USD");
		trade.setPremiumType("%USD");
		trade.setPremiumDate(LocalDate.parse("2016-08-12"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.EXPIRY_DATE_AFTER_DELIVERY_DATE);
	}
	
	@Test
	public void shouldReportPremiumDateAfterDeliveryDate() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("VanillaOption");
		trade.setStyle("AMERICAN");
		trade.setDirection("BUY");
		trade.setStrategy("CALL");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setDeliveryDate(LocalDate.parse("2016-08-22"));
		trade.setExpiryDate(LocalDate.parse("2016-08-19"));
		trade.setExcerciseStartDate(LocalDate.parse("2016-08-12"));
		trade.setPayCcy("USD");
		trade.setPremium(new BigDecimal("0.20"));
		trade.setPremiumCcy("USD");
		trade.setPremiumType("%USD");
		trade.setPremiumDate(LocalDate.parse("2016-08-23"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().iterator().next().getErrorCode()).isEqualTo(ErrorCode.PREMIUM_DATE_AFTER_DELIVERY_DATE);
	}
	
	@Test
	public void shouldReportTwoErrors() throws InvalidCurrencyException {
		//given
		Trade trade = new Trade();
		trade.setCustomer("PLUTO1");
		trade.setCcyPair("EURUSD");
		trade.setType("VanillaOption");
		trade.setStyle("AMERICAN");
		trade.setDirection("BUY");
		trade.setStrategy("CALL");
		trade.setTradeDate(LocalDate.parse("2016-08-11"));
		trade.setAmount1(new BigDecimal("1000000.00"));
		trade.setAmount2(new BigDecimal("1120000.00"));
		trade.setRate(new BigDecimal("1.12"));
		trade.setDeliveryDate(LocalDate.parse("2016-08-22"));
		trade.setExpiryDate(LocalDate.parse("2016-08-23"));
		trade.setExcerciseStartDate(LocalDate.parse("2016-08-12"));
		trade.setPayCcy("USD");
		trade.setPremium(new BigDecimal("0.20"));
		trade.setPremiumCcy("USD");
		trade.setPremiumType("%USD");
		trade.setPremiumDate(LocalDate.parse("2016-08-23"));
		trade.setLegalEntity("CS Zurich");
		trade.setTrader("Johann Baumfiddler");
		
		//when
		ErrorTrade result = validationService.validate(trade);
		
		//then
		assertThat(result.getErrors().size()).isEqualTo(2);
	}
}
