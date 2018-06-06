package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="mobile_app_demos")
@org.hibernate.annotations.Proxy(lazy=false)
public class MobileAppDemos implements Serializable {
	
	private static final long serialVersionUID = 5098154242838123207L;

	public MobileAppDemos() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B75140955F51CE05025")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B75140955F51CE05025", strategy="identity")	
	private int id;
	
	@Column(name="client_id", nullable=false)	
	@org.hibernate.annotations.Index(name="FK_admin_login_client_id")	
	private int client_id;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	/**
	 * Client ID
	 */
	public void setClient_id(int value) {
		this.client_id = value;
	}
	
	/**
	 * Client ID
	 */
	public int getClient_id() {
		return client_id;
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
			sb.append("Mobile_app_demos[ ");
			sb.append("Id=").append(getId()).append(" ");
			sb.append("Client_id=").append(getClient_id()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
	
}
