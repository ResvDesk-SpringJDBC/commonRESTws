package com.telappoint.commonrestws.common.controller.json;
/**
 * 
 * @author Murali
 *
 */
public enum StatusCodes {
	ZERO_ZERO("00", "no error"), 
	ZERO_ONE("01", "Authentication failure!"),
	
	NINETY_SEVEN("97","Token got expired"),
	NINETY_EIGHT("98", "Internal Server Error"),
	NINETY_NINE("99", "DB error"),

	EIGHTY_ONE("81",""),
	EIGHTY_TWO("82",""),
	EIGHTY_THREE("83",""),
	EIGHTY_FOUR("84","");
	
	private String statusCode;
	private String description;

	private StatusCodes(String statusCode, String description) {
		this.statusCode = statusCode;
		this.description = description;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getDescription() {
		return description;
	}

}
