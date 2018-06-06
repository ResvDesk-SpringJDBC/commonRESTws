package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="page_clicks")
@org.hibernate.annotations.Proxy(lazy=false)
public class PageClicks implements Serializable {
		
	private static final long serialVersionUID = -2140795647208480806L;

	public PageClicks() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B75140955F51CE05026")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B75140955F51CE05026", strategy="identity")	
	private int id;
	
	@Column(name="app_name", nullable=false, length=10)	
	private String app_name;
	
	@Column(name="page_id", nullable=false)	
	private int page_id;
	
	@Column(name="page_name", nullable=false, length=50)	
	private String page_name;
	
	@Column(name="click_id", nullable=false)	
	private int click_id;
	
	@Column(name="click_name", nullable=false, length=100)	
	private String click_name;
	
	@Column(name="front_end_method_name", nullable=false, length=100)	
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

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getId());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("Activity_log[ ");
			sb.append("Id=").append(getId()).append(", ");
			sb.append("App_name=").append(getApp_name()).append(" ,");
			sb.append("page_id=").append(getPage_id()).append(" ,");
			sb.append("page_name=").append(getPage_name()).append(", ");
			sb.append("click_id=").append(getClick_id()).append(", ");
			sb.append("click_name=").append(getClick_name()).append(", ");
			sb.append("Front_end_method_name=").append(getFront_end_method_name()).append(",");
			sb.append("]");
			return sb.toString();
		}
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
