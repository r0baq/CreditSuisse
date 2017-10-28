package org.mroczkarobert.creditsuisse.transport;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.mroczkarobert.creditsuisse.type.ProductType;
import org.mroczkarobert.creditsuisse.util.LocalDateDeserializer;
import org.mroczkarobert.creditsuisse.util.ProductTypeDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Trade { //RMR serializable?
	
	@JsonDeserialize(using = ProductTypeDeserializer.class)  
	private ProductType type;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	private LocalDate tradeDate;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	private LocalDate valueDate;
	
	private String customer; //RMR enums?
	private String ccyPair; //RMR enums?
	private String direction; //RMR enums?
	private BigDecimal amount1;
	private BigDecimal amount2;
	private BigDecimal rate;
	private String legalEntity; //RMR enum?
	private String trader;
	
	private String style;
	private String strategy;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	private LocalDate deliveryDate;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	private LocalDate expiryDate;
	private String payCcy;
	private BigDecimal premium;
	private String premiumCcy;
	private String premiumType;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	private LocalDate premiumDate;
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	private LocalDate excerciseStartDate;
	
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
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
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
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getPayCcy() {
		return payCcy;
	}
	public void setPayCcy(String payCcy) {
		this.payCcy = payCcy;
	}
	public BigDecimal getPremium() {
		return premium;
	}
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	public String getPremiumCcy() {
		return premiumCcy;
	}
	public void setPremiumCcy(String premiumCcy) {
		this.premiumCcy = premiumCcy;
	}
	public String getPremiumType() {
		return premiumType;
	}
	public void setPremiumType(String premiumType) {
		this.premiumType = premiumType;
	}
	public LocalDate getPremiumDate() {
		return premiumDate;
	}
	public void setPremiumDate(LocalDate premiumDate) {
		this.premiumDate = premiumDate;
	}
	public LocalDate getExcerciseStartDate() {
		return excerciseStartDate;
	}
	public void setExcerciseStartDate(LocalDate excerciseStartDate) {
		this.excerciseStartDate = excerciseStartDate;
	}
	@Override
	public String toString() {
		return "Trade [type=" + type + ", tradeDate=" + tradeDate + ", valueDate=" + valueDate + ", customer="
				+ customer + ", ccyPair=" + ccyPair + ", direction=" + direction + ", amount1=" + amount1 + ", amount2="
				+ amount2 + ", rate=" + rate + ", legalEntity=" + legalEntity + ", trader=" + trader + ", style="
				+ style + ", strategy=" + strategy + ", deliveryDate=" + deliveryDate + ", expiryDate=" + expiryDate
				+ ", payCcy=" + payCcy + ", premium=" + premium + ", premiumCcy=" + premiumCcy + ", premiumType="
				+ premiumType + ", premiumDate=" + premiumDate + ", excerciseStartDate=" + excerciseStartDate + "]";
	}
}
