package org.mroczkarobert.creditsuisse.type;

public enum ProductType {
	SPOT("Spot"),
	FORWARD("Forward"),
	OPTION("Option");
	
	private String jsonValue;
	
	private ProductType(String jsonValue) {
		this.jsonValue = jsonValue;
	}

	public String getJsonValue() {
		return jsonValue;
	}
}
