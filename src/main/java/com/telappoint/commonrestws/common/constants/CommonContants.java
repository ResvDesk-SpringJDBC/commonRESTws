package com.telappoint.commonrestws.common.constants;

/**
 * @author Murali
 */

public enum CommonContants {
	
	VERSION("version"),
	EMPTY_STRING("");
	
	private String value;

	private CommonContants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
