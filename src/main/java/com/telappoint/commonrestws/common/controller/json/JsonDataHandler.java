package com.telappoint.commonrestws.common.controller.json;

import static com.telappoint.commonrestws.common.controller.json.MessageManager.getProperty;

import java.util.Collection;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.telappoint.commonrestws.common.constants.CommonContants;

/**
 * 
 * @author Rajin
 * @author Balaji
 */

@JsonAutoDetect
@JsonSerialize(include = Inclusion.NON_NULL)
public class JsonDataHandler {
	private Object data;
	private String version;
	private String statusCode;
	private String description;
	private String messageType;
	private int totalCount;

	public JsonDataHandler(Object data, String messageType, String statusCode) {
		this.data = data;
		this.setDescription(getProperty(messageType, statusCode));
		this.setStatusCode(statusCode);
	}

	public JsonDataHandler(String errorCode) {
		this(null, MessageType.INFO.getMessageType(), errorCode);
	}

	public JsonDataHandler() {
		this(StatusCodes.ZERO_ZERO.getStatusCode());
	}

	public static JsonDataHandler SUCCESS_RESPONSE() {
		JsonDataHandler jsonData = new JsonDataHandler();
		jsonData.setMessageType(MessageType.INFO.getMessageType());
		jsonData.setStatusCode(StatusCodes.ZERO_ZERO.getStatusCode());
		//jsonData.setDescription(getProperty(MessageType.INFO.getMessageType(), LoginConstants.LOGIN_SUCESSES_RESPONSE.getValue()));
		jsonData.setVersion(getProperty(MessageType.INFO.getMessageType(), CommonContants.VERSION.getValue()));
		return jsonData;
	}

	public static JsonDataHandler SUCCESS_RESPONSE(String statusCode) {
		JsonDataHandler jsonData = new JsonDataHandler();
		jsonData.setMessageType(MessageType.INFO.getMessageType());
		jsonData.setStatusCode(StatusCodes.ZERO_ZERO.getStatusCode());
		jsonData.setDescription(getProperty(MessageType.INFO.getMessageType(), statusCode));
		jsonData.setVersion(getProperty(MessageType.INFO.getMessageType(),CommonContants.VERSION.getValue()));
		return jsonData;
	}

	public static JsonDataHandler FAILURE_RESPONSE() {
		JsonDataHandler jsonData = new JsonDataHandler();
		jsonData.setMessageType(MessageType.ERROR.getMessageType());
		jsonData.setStatusCode(StatusCodes.ZERO_ONE.getStatusCode());
		jsonData.setDescription(getProperty(MessageType.ERROR.getMessageType(), StatusCodes.ZERO_ONE.getStatusCode()));
		jsonData.setVersion(getProperty(MessageType.ERROR.getMessageType(),CommonContants.VERSION.getValue()));
		return jsonData;
	}
	
	public static JsonDataHandler FAILURE_RESPONSE(String messageType,String statusCode) {
		JsonDataHandler jsonData = new JsonDataHandler();
		jsonData.setMessageType(messageType);
		jsonData.setStatusCode(statusCode);
		jsonData.setDescription(getProperty(messageType, statusCode));
		jsonData.setVersion(getProperty(messageType,CommonContants.VERSION.getValue()));
		return jsonData;
	}
	
	public static JsonDataHandler VALIDATION_FAILURE_RESPONSE(String statusCode) {
		JsonDataHandler jsonData = new JsonDataHandler();
		jsonData.setMessageType(MessageType.ERROR.getMessageType());
		jsonData.setStatusCode(statusCode);
		jsonData.setDescription(getProperty(MessageType.ERROR.getMessageType(), statusCode));
		jsonData.setVersion(getProperty(MessageType.ERROR.getMessageType(),CommonContants.VERSION.getValue()));
		return jsonData;
	}

	public static JsonDataHandler AUTHENTICATE_FAILED_RESPONSE(String statusType, String statusCode) {
		JsonDataHandler jsonData = new JsonDataHandler();
		jsonData.setMessageType(statusType);
		jsonData.setStatusCode(statusCode);
		jsonData.setDescription(getProperty(statusType, statusCode));
		jsonData.setVersion(getProperty(statusType,CommonContants.VERSION.getValue()));
		return jsonData;
	}

	public static JsonDataHandler EXCEPTION_RESPONSE(String errorCode) {
		JsonDataHandler jsonData = new JsonDataHandler();
		jsonData.setMessageType(MessageType.ERROR.getMessageType());
		jsonData.setStatusCode(errorCode);
		jsonData.setDescription(getProperty(MessageType.ERROR.getMessageType(), errorCode));
		jsonData.setVersion(getProperty(MessageType.ERROR.getMessageType(),CommonContants.VERSION.getValue()));
		return jsonData;
	}

	public static JsonDataHandler EXCEPTION_RESPONSE(String errorCode, String errorMessage) {
		JsonDataHandler jsonData = new JsonDataHandler();
		jsonData.setMessageType(MessageType.ERROR.getMessageType());
		jsonData.setStatusCode(errorCode);
		jsonData.setDescription(errorMessage);
		jsonData.setVersion(getProperty(MessageType.ERROR.getMessageType(),CommonContants.VERSION.getValue()));
		return jsonData;
	}
	
	public static JsonDataHandler TOKEN_EXPIRED() {
		JsonDataHandler jsonData = new JsonDataHandler();
		jsonData.setMessageType(MessageType.ERROR.getMessageType());
		jsonData.setStatusCode(StatusCodes.NINETY_SEVEN.getStatusCode());
		jsonData.setDescription(getProperty(MessageType.ERROR.getMessageType(),StatusCodes.NINETY_SEVEN.getStatusCode()));
		jsonData.setVersion(getProperty(MessageType.ERROR.getMessageType(),CommonContants.VERSION.getValue()));
		return jsonData;
	}

	public JsonDataHandler withData(Object data) {
		this.data = data;
		return this;
	}

	public JsonDataHandler withCollection(Object data) {
		this.data = data;
		this.totalCount = ((Collection<?>) data).size();
		return this;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
