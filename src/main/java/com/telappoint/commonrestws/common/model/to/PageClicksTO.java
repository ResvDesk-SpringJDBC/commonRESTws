package com.telappoint.commonrestws.common.model.to;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author Murali G
 * 
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class PageClicksTO {

	
	private int id;
	private String app_name;
	private int page_id;
	private String page_name;
	private int click_id;
	private String click_name;
	private String front_end_method_name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPage_id() {
		return page_id;
	}
	public void setPage_id(int page_id) {
		this.page_id = page_id;
	}
	public String getPage_name() {
		return page_name;
	}
	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}
	public int getClick_id() {
		return click_id;
	}
	public void setClick_id(int click_id) {
		this.click_id = click_id;
	}
	public String getClick_name() {
		return click_name;
	}
	public void setClick_name(String click_name) {
		this.click_name = click_name;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getFront_end_method_name() {
		return front_end_method_name;
	}
	public void setFront_end_method_name(String front_end_method_name) {
		this.front_end_method_name = front_end_method_name;
	}

}
