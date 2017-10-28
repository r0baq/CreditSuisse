package org.mroczkarobert.creditsuisse.transport;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.mroczkarobert.creditsuisse.util.LocalDateDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Trade { //RMR serializable?
	
	private String customer; //RMR enums?
	
	private String ccyPair; //RMR enums?
	
	private String type; //RMR enums?
	
	private String direction; //RMR enums?
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	private LocalDate tradeDate;
	
	private BigDecimal amount1;
	
	private BigDecimal amount2;
	
	private BigDecimal rate;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	private LocalDate valueDate;
	
	private String legalEntity; //RMR enum?
	
	private String trader;
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCcyPair() {
		return ccyPair;
	}
	public void setCcyPair(String ccyPair) {
		this.ccyPair = ccyPair;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public LocalDate getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(LocalDate tradeDate) {
		this.tradeDate = tradeDate;
	}
	public BigDecimal getAmount1() {
		return amount1;
	}
	public void setAmount1(BigDecimal amount1) {
		this.amount1 = amount1;
	}
	public BigDecimal getAmount2() {
		return amount2;
	}
	public void setAmount2(BigDecimal amount2) {
		this.amount2 = amount2;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public LocalDate getValueDate() {
		return valueDate;
	}
	public void setValueDate(LocalDate valueDate) {
		this.valueDate = valueDate;
	}
	public String getLegalEntity() {
		return legalEntity;
	}
	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
	}
	public String getTrader() {
		return trader;
	}
	public void setTrader(String trader) {
		this.trader = trader;
	}
	
	@Override
	public String toString() {
		return "Trade [customer=" + customer + ", ccyPair=" + ccyPair + ", type=" + type + ", direction=" + direction
				+ ", tradeDate=" + tradeDate + ", amount1=" + amount1 + ", amount2=" + amount2 + ", rate=" + rate
				+ ", valueDate=" + valueDate + ", legalEntity=" + legalEntity + ", trader=" + trader + "]";
	}
}
