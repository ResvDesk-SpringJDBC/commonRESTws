package com.telappoint.commonrestws.common.masterdb.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="system_personnel")
@org.hibernate.annotations.Proxy(lazy=false)
public class SystemPersonnel implements Serializable {

	private static final long serialVersionUID = 1L;
	public SystemPersonnel() {}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A84B3513C946466840D7EA")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A84B3513C946466840D7EA", strategy="identity")	
	private Integer id;
	
	@Column(name="name", nullable=false, length=100)	
	private String name;
	
	@Column(name="is_deployer", nullable=true, length=1)	
	private Character is_deployer = new Character('N');
	
	@Column(name="is_tester", nullable=true, length=1)	
	private Character is_tester = new Character('N');
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getIs_deployer() {
		return is_deployer;
	}

	public void setIs_deployer(Character is_deployer) {
		this.is_deployer = is_deployer;
	}

	public Character getIs_tester() {
		return is_tester;
	}

	public void setIs_tester(Character is_tester) {
		this.is_tester = is_tester;
	}
		
}
