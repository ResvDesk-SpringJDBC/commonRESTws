
package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="login_attempts")
@org.hibernate.annotations.Proxy(lazy=false)
public class LoginAttempts implements Serializable {
	
	private static final long serialVersionUID = -2973232798630940558L;

	public LoginAttempts() {
	}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A80B75140955F51CE05024")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A80B75140955F51CE05024", strategy="identity")	
	private int id;
	
	@Column(name="user_id", nullable=false)	
	@org.hibernate.annotations.Index(name="user_id")	
	private int user_id;
	
	@Column(name="timestamp", nullable=false)	
	private java.sql.Timestamp timestamp;
	
	@Column(name="ip_address", nullable=false, length=50)	
	private String ip_address;
	
	@Column(name="login_status", nullable=true, length=50)	
	private String login_status;
	
	public void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setUser_id(int value) {
		this.user_id = value;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	public void setTimestamp(java.sql.Timestamp value) {
		this.timestamp = value;
	}
	
	public java.sql.Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setIp_address(String value) {
		this.ip_address = value;
	}
	
	public String getIp_address() {
		return ip_address;
	}
	
	public void setLogin_status(String value) {
		this.login_status = value;
	}
	
	public String getLogin_status() {
		return login_status;
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
			sb.append("Login_attempts[ ");
			sb.append("Id=").append(getId()).append(" ");
			sb.append("User_id=").append(getUser_id()).append(" ");
			sb.append("Timestamp=").append(getTimestamp()).append(" ");
			sb.append("Ip_address=").append(getIp_address()).append(" ");
			sb.append("Login_status=").append(getLogin_status()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
	
}
