package com.telappoint.commonrestws.support.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
@org.hibernate.annotations.Proxy(lazy = false)
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	public Ticket() {
	}

	@Column(name = "id", nullable = false)
	@Id
	@GeneratedValue(generator = "VC0A84B3513CA8B2CE9509183")
	@org.hibernate.annotations.GenericGenerator(name = "VC0A84B3513CA8B2CE9509183", strategy = "identity")
	private Integer id;

	@Column(name = "timestamp", nullable = false)
	private Timestamp timestamp;
	
	@Column(name = "subject", nullable = true, length = 3)
	private String subject;
	
	@Column(name = "date_opened", nullable = false)
	private Timestamp date_opened;
	
	@Column(name = "date_updated", nullable = false)
	private Timestamp date_updated;
	
	@Column(name = "client_code", nullable = true, length = 8)
	private String client_code;
	
	@Column(name = "client_name", nullable = true, length = 100)
	private String client_name;
	
	@Column(name = "username", nullable = true, length = 30)
	private String username;
		
	@Column(name = "priority", nullable = false, length =4)
	private int priority;	

	@ManyToOne(fetch = FetchType.LAZY, cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })	
	@JoinColumns({ @JoinColumn(name="type_id", referencedColumnName="id") })	
	private SupportTypes supportTypes;
	
	/*@Column(name = "status", nullable = false, length = 4)
	private int status;*/
	
	@Column(name = "contact_phone", nullable = false, length =15)
	private String contact_phone;
	
	@Column(name = "contact_email", nullable = false, length = 100)
	private String contact_email;
	
	@Column(name = "escalate_request", nullable = false, length = 1)
	private char escalate_request='N';
	
	@Column(name = "comment", nullable = false)
	private String comment;
	
	@Column(name = "itfd_response_1", nullable = true)
	private String itfd_response_1;
	
	@Column(name = "client_response_1_enable", nullable = false, length = 1)
	private char client_response_1_enable='N';
	
	@Column(name = "client_response_1 ", nullable = true)
	private String client_response_1 ;
	
	@Column(name = "itfd_response_2", nullable = true)
	private String itfd_response_2;
	
	@Column(name = "client_response_2_enable", nullable = false, length = 1)
	private char client_response_2_enable='N';
	
	@Column(name = "client_response_2", nullable = true)
	private String client_response_2 ;
	
	@Column(name = "itfd_response_3", nullable = true)
	private String itfd_response_3;
	
	@Column(name = "client_response_3_enable", nullable = false, length = 1)
	private char client_response_3_enable='N';
	
	@Column(name = "client_response_3", nullable = true)
	private String client_response_3;
	
	@Column(name = "itfd_response_4", nullable = true)
	private String itfd_response_4;
	
	@Column(name = "client_response_4_enable", nullable = false, length = 1)
	private char client_response_4_enable='N';
	
	@Column(name = "client_response_4", nullable = true)
	private String client_response_4 ;
	
	@Column(name = "itfd_response_5", nullable = true)
	private String itfd_response_5;
	
	@Column(name = "client_response_5_enable", nullable = false, length = 1)
	private char client_response_5_enable='N';
	
	@Column(name = "client_response_5", nullable = true)
	private String client_response_5 ;
	
	@Column(name = "file_attachment_1", nullable = true)
	private String file_attachment_1 ;
	
	@Column(name = "file_attachment_2", nullable = true)
	private String file_attachment_2 ;
	
	@Column(name = "file_attachment_3", nullable = true)
	private String file_attachment_3;
	
	@Column(name = "file_attachment_4", nullable = true)
	private String file_attachment_4;
	
	@Column(name = "file_attachment_5", nullable = true)
	private String file_attachment_5;
		
	@ManyToOne(fetch = FetchType.LAZY, cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })	
	@JoinColumns({ @JoinColumn(name="status_id", referencedColumnName="id") })	
	private SupportStatus supportStatus;
	
	public Integer getId() {
		return id;
	}

	public SupportStatus getSupportStatus() {
		return supportStatus;
	}

	public void setSupportStatus(SupportStatus supportStatus) {
		this.supportStatus = supportStatus;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Timestamp getDate_opened() {
		return date_opened;
	}

	public void setDate_opened(Timestamp date_opened) {
		this.date_opened = date_opened;
	}

	public Timestamp getDate_updated() {
		return date_updated;
	}

	public void setDate_updated(Timestamp date_updated) {
		this.date_updated = date_updated;
	}

	public String getClient_code() {
		return client_code;
	}

	public void setClient_code(String client_code) {
		this.client_code = client_code;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	/*public int getStatus() {
		return status;
	}*/

	public SupportTypes getSupportTypes() {
		return supportTypes;
	}

	public void setSupportTypes(SupportTypes supportTypes) {
		this.supportTypes = supportTypes;
	}

	/*public void setStatus(int status) {
		this.status = status;
	}*/

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}

	public char getEscalate_request() {
		return escalate_request;
	}

	public void setEscalate_request(char escalate_request) {
		this.escalate_request = escalate_request;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getItfd_response_1() {
		return itfd_response_1;
	}

	public void setItfd_response_1(String itfd_response_1) {
		this.itfd_response_1 = itfd_response_1;
	}

	public char getClient_response_1_enable() {
		return client_response_1_enable;
	}

	public void setClient_response_1_enable(char client_response_1_enable) {
		this.client_response_1_enable = client_response_1_enable;
	}

	public String getClient_response_1() {
		return client_response_1;
	}

	public void setClient_response_1(String client_response_1) {
		this.client_response_1 = client_response_1;
	}

	public String getItfd_response_2() {
		return itfd_response_2;
	}

	public void setItfd_response_2(String itfd_response_2) {
		this.itfd_response_2 = itfd_response_2;
	}

	public char getClient_response_2_enable() {
		return client_response_2_enable;
	}

	public void setClient_response_2_enable(char client_response_2_enable) {
		this.client_response_2_enable = client_response_2_enable;
	}

	public String getClient_response_2() {
		return client_response_2;
	}

	public void setClient_response_2(String client_response_2) {
		this.client_response_2 = client_response_2;
	}

	public String getItfd_response_3() {
		return itfd_response_3;
	}

	public void setItfd_response_3(String itfd_response_3) {
		this.itfd_response_3 = itfd_response_3;
	}

	public char getClient_response_3_enable() {
		return client_response_3_enable;
	}

	public void setClient_response_3_enable(char client_response_3_enable) {
		this.client_response_3_enable = client_response_3_enable;
	}

	public String getClient_response_3() {
		return client_response_3;
	}

	public void setClient_response_3(String client_response_3) {
		this.client_response_3 = client_response_3;
	}

	public String getItfd_response_4() {
		return itfd_response_4;
	}

	public void setItfd_response_4(String itfd_response_4) {
		this.itfd_response_4 = itfd_response_4;
	}

	public char getClient_response_4_enable() {
		return client_response_4_enable;
	}

	public void setClient_response_4_enable(char client_response_4_enable) {
		this.client_response_4_enable = client_response_4_enable;
	}

	public String getClient_response_4() {
		return client_response_4;
	}

	public void setClient_response_4(String client_response_4) {
		this.client_response_4 = client_response_4;
	}

	public String getItfd_response_5() {
		return itfd_response_5;
	}

	public void setItfd_response_5(String itfd_response_5) {
		this.itfd_response_5 = itfd_response_5;
	}

	public char getClient_response_5_enable() {
		return client_response_5_enable;
	}

	public void setClient_response_5_enable(char client_response_5_enable) {
		this.client_response_5_enable = client_response_5_enable;
	}

	public String getClient_response_5() {
		return client_response_5;
	}

	public void setClient_response_5(String client_response_5) {
		this.client_response_5 = client_response_5;
	}

	public String getFile_attachment_1() {
		return file_attachment_1;
	}

	public void setFile_attachment_1(String file_attachment_1) {
		this.file_attachment_1 = file_attachment_1;
	}

	public String getFile_attachment_2() {
		return file_attachment_2;
	}

	public void setFile_attachment_2(String file_attachment_2) {
		this.file_attachment_2 = file_attachment_2;
	}

	public String getFile_attachment_3() {
		return file_attachment_3;
	}

	public void setFile_attachment_3(String file_attachment_3) {
		this.file_attachment_3 = file_attachment_3;
	}

	public String getFile_attachment_4() {
		return file_attachment_4;
	}

	public void setFile_attachment_4(String file_attachment_4) {
		this.file_attachment_4 = file_attachment_4;
	}

	public String getFile_attachment_5() {
		return file_attachment_5;
	}

	public void setFile_attachment_5(String file_attachment_5) {
		this.file_attachment_5 = file_attachment_5;
	}

	public String toString() {
		return toString(true);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getId());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("Ticket[ ");
			sb.append("Id=").append(getId()).append(" ");
			sb.append("Timestamp=").append(getTimestamp()).append(" ");
			//sb.append("Status=").append(getStatus()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

}
