package com.telappoint.commonrestws.common.constants;

/**
 * 
 * @author Murali
 * 
 */

public enum CommonDateContants {
	EMPTY_STRING(""),
	DATETIME_FORMAT_YYYYMMDDHHMMSS_WITH_AMPM("yyyy-MM-dd hh:mm:ss a"),
	DATETIME_FORMAT_YYYYMMDDHHMMSS("yyyy-MM-dd hh:mm:ss"),
	DATETIME_FORMAT_YYYYMMDDHHMMSS_CAP("yyyy-MM-dd HH:mm:ss"),
	DATE_FORMAT_YYYYMMDD("yyyy-MM-dd"),
	TIME_FORMAT_HHMMSS("hh:mm:ss"),
	TIME_FORMAT_HHMMSS_CAP("HH:mm:ss"),
	TIME_FORMAT_TWELVE_HRS("hh:mm a"),
	TIME_FORMAT_TWENTY_FOUR_HRS("HH:mm"),
	DATE_FORMAT_DDMMMYYYY("dd-MMM-yyyy"),
	DAY_OF_THE_WEEK_FORMAT("E"),
	DATE_FORMAT_MMDDYYYY("MM/dd/yyyy");

	private String value;

	private CommonDateContants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
