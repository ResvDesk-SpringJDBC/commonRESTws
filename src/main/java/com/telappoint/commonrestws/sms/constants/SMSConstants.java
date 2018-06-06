package com.telappoint.commonrestws.sms.constants;

/**
 * 
 * @author Murali
 * 
 */

public enum SMSConstants {
	SMS_ACCOUNT_DETAILS_PROP_FILE("sms-account-details.properties"),
	SMS_ACCOUNT_SID_PROP_KEY("accountsid"),
	SMS_AUTH_TOKEN_PROP_KEY("authToken"),
	SMS_SUCESSES_RESPONSE("sucesses"),
	SMS_FAILURE_RESPONSE("failure");


	private String value;

	private SMSConstants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}


}
