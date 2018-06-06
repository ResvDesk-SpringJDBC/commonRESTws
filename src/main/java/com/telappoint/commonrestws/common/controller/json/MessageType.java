package com.telappoint.commonrestws.common.controller.json;

/**
 * 
 * @author Balaji
 * 
 */
public enum MessageType {
	INFO("info"), WARNING("warn"), ERROR("error");

	private String messageType;

	private MessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the statusType
	 */
	public String getMessageType() {
		return messageType;
	}
}
