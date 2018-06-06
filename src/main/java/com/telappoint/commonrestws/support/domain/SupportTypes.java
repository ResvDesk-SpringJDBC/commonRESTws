package com.telappoint.commonrestws.support.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="support_types")
@org.hibernate.annotations.Proxy(lazy=false)
public class SupportTypes implements Serializable {
	private static final long serialVersionUID = 1L;

	public SupportTypes() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B6D1404E7A63C403DF7")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B6D1404E7A63C403DF7", strategy="identity")	
	private int id;
	
	@Column(name="type", nullable=false, length=90)	
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getId());
		}
		else {
			StringBuffer sb = new StringBuffer();
			sb.append("AccessPrivilege[ ");
			sb.append("Id=").append(getId()).append(" ");
			sb.append("Type=").append(getType()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}	
}
