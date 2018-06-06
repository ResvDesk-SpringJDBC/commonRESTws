package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="request_response_time_log")
@org.hibernate.annotations.Proxy(lazy=false)
public class RequestResponseTimeLog implements Serializable {
		
	private static final long serialVersionUID = -2140795647208480806L;

	public RequestResponseTimeLog() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B75140955F51CE05026")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B75140955F51CE05026", strategy="identity")	
	private int id;
	
	@Column(name="timestamp", nullable=false)	
	private java.sql.Timestamp timestamp;
	
	@Column(name="userid", nullable=false)	
	private int userid;
	
	@Column(name="client_code", nullable=false, length=10)	
	private String client_code;
	
	@Column(name="app_name", nullable=false, length=10)	
	private String app_name;
	
	@Column(name="sessionid", nullable=false, length=100)	
	private String sessionid;
	
	@Column(name="page", nullable=true, length=50)	
	private String page;
	
	@Column(name="api", nullable=true, length=100)	
	private String api;
	
	@Column(name="method_type", nullable=false, length=10)	
	private String method_type;
	
	@Column(name="url", nullable=false, length=500)	
	private String url;
	
	@Column(name="json_request")	
	private String json_request;
	
	@Column(name="json_response")	
	private String json_response;
	
	@Column(name="duration_ms", nullable=false)	
	private int duration_ms;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(java.sql.Timestamp timestamp) {
		this.timestamp = timestamp;
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
