package com.telappoint.commonrestws.common.model.to;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 
 * @author Murali G
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class RequestResponseTimeLogTO {
		
	private int id;	
	private String timestampStr;	
	private int userid;	
	private String client_code;
	private String app_name;
	private String sessionid;
	private String page;
	private String api;
	private String method_type;
	private String url;
	private String json_request;
	private String json_response;
	private int duration_ms;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTimestampStr() {
		return timestampStr;
	}
	public void setTimestampStr(String timestampStr) {
		this.timestampStr = timestampStr;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getClient_code() {
		return client_code;
	}
	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public String getMethod_type() {
		return method_type;
	}
	public void setMethod_type(String method_type) {
		this.method_type = method_type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getJson_response() {
		return json_response;
	}
	public void setJson_response(String json_response) {
		this.json_response = json_response;
	}
	public int getDuration_ms() {
		return duration_ms;
	}
	public void setDuration_ms(int duration_ms) {
		this.duration_ms = duration_ms;
	}
	public String getJson_request() {
		return json_request;
	}
	public void setJson_request(String json_request) {
		this.json_request = json_request;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
}
