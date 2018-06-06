package com.telappoint.commonrestws.support.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="support_email_templates")
@org.hibernate.annotations.Proxy(lazy=false)
public class SupportEmailTemplates implements Serializable {
	private static final long serialVersionUID = 1L;

	public SupportEmailTemplates() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A84B3513CA8B2CDF90916E")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A84B3513CA8B2CDF90916E", strategy="identity")	
	private int id;
	
	@Column(name="type", nullable=true, length=50)	
	private String type;
	
	@Column(name="subject", nullable=true, length=50)
	private String subject;
	
	@Column(name="body", nullable=true, length=2000)	
	private String body;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String toString() {
		return toString(false);
	}
	
	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getId());
		}
		else {
			StringBuffer sb = new StringBuffer();
			sb.append("I18n_email_templates[ ");
			sb.append("Id = ").append(getId()).append(" ");
			sb.append("Type = ").append(getType()).append(" ");
			sb.append("Subject = ").append(getSubject()).append(" ");
			sb.append("Body = ").append(getBody()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
	
}
