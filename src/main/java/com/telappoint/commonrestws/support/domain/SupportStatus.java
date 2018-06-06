package com.telappoint.commonrestws.support.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="support_status")
@org.hibernate.annotations.Proxy(lazy=false)
public class SupportStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	public SupportStatus() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B6D1404E7A63C403DF7")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B6D1404E7A63C403DF7", strategy="identity")	
	private int id;
	
	@Column(name="status", nullable=false, length=90)	
	private String status;
	
	@Column(name="value", nullable=false)	
	private int value;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getId());
		}
		else {
			StringBuffer sb = new StringBuffer();
			sb.append("AccessPrivilege[ ");
			sb.append("Id=").append(getId()).append(" ");
			sb.append("Status=").append(getStatus()).append(" ");
			sb.append("Value=").append(getValue()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}	
}
