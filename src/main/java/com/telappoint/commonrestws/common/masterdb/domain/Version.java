package com.telappoint.commonrestws.common.masterdb.domain;

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
@Table(name="version")
@org.hibernate.annotations.Proxy(lazy=false)
public class Version implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public Version() {}
	
	@Column(name="id", nullable=false)	
	@Id	
	@GeneratedValue(generator="VC0A84B3513C946466840D7EB")	
	@org.hibernate.annotations.GenericGenerator(name="VC0A84B3513C946466840D7EB", strategy="identity")	
	private int id;
		
	@Column(name="app_code", nullable=false, length=100)	
	private String app_code; //'APPTDESK, NOTIFYDESK, RESVDESK, LABRESDESK'
	
	@Column(name="timestamp", nullable=false)	
	private Timestamp timestamp;
	
	@Column(name="deployment_scheduled", nullable=false)	
	private Timestamp deployment_scheduled;
	
	@Column(name="deployment_timestamp", nullable=true)	
	private Timestamp deployment_timestamp;
	
	/*@Column(name="deployment_person", nullable=false, length=100)	
	private String deployment_person;	
	@Column(name="testing_person", nullable=false, length=100)	
	private String testing_person;*/
	
	@ManyToOne(cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },fetch=FetchType.LAZY)	
	@JoinColumns({ @JoinColumn(name="testing_person", referencedColumnName="name") })	
	private SystemPersonnel testing_person;
	
	@ManyToOne(cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },fetch=FetchType.LAZY)	
	@JoinColumns({ @JoinColumn(name="deployment_person", referencedColumnName="name") })	
	private SystemPersonnel deployment_person;
	
	@Column(name="war_file_1", nullable=true, length=100)	
	private String war_file_1;
	
	@Column(name="war_file_1_svn_revision", nullable=true)	
	private Integer war_file_1_svn_revision;
	
	@Column(name="war_file_2", nullable=true, length=100)	
	private String war_file_2;
	
	@Column(name="war_file_2_svn_revision", nullable=true)	
	private Integer war_file_2_svn_revision;
	
	@Column(name="war_file_3", nullable=true, length=100)	
	private String war_file_3;
	
	@Column(name="war_file_3_svn_revision", nullable=true)	
	private Integer war_file_3_svn_revision;
	
	@Column(name="war_file_4", nullable=true, length=100)	
	private String war_file_4;
	
	@Column(name="war_file_4_svn_revision", nullable=true)	
	private Integer war_file_4_svn_revision;
	
	@Column(name="war_file_5", nullable=true, length=100)	
	private String war_file_5;
	
	@Column(name="war_file_5_svn_revision", nullable=true)	
	private Integer war_file_5_svn_revision;
	
	@Column(name="war_file_6", nullable=true, length=100)	
	private String war_file_6;
	
	@Column(name="war_file_6_svn_revision", nullable=true)	
	private Integer war_file_6_svn_revision;
	
	@Column(name="svn_tag_name", nullable=true, length=100)	
	private String svn_tag_name;
	
	@Column(name="is_tested", nullable=true, length=1)	
	private Character is_tested='N';
	
	@Column(name="major_version", nullable=false)	
	private int major_version;
	
	@Column(name="minor_version", nullable=false)	
	private int minor_version;
	
	@Column(name="patch_version", nullable=false)	
	private int patch_version;
	
	@Column(name="major_bug_fixes", nullable=true)	
	private String major_bug_fixes;
	
	@Column(name="minor_bug_fixes", nullable=true)	
	private String minor_bug_fixes;
	
	@Column(name="patch_bug_fixes", nullable=true)	
	private String patch_bug_fixes;
	
	@Column(name="contact_email", nullable=false, length=100)	
	private String contact_email;
	
	@Column(name="rolled_back", nullable=false, length=1)	
	private Character rolled_back='N';
	
	@Column(name="rollback_version", nullable=true, length=100)	
	private String rollback_version;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApp_code() {
		return app_code;
	}

	public void setApp_code(String app_code) {
		this.app_code = app_code;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Timestamp getDeployment_scheduled() {
		return deployment_scheduled;
	}

	public void setDeployment_scheduled(Timestamp deployment_scheduled) {
		this.deployment_scheduled = deployment_scheduled;
	}

	public Timestamp getDeployment_timestamp() {
		return deployment_timestamp;
	}

	public void setDeployment_timestamp(Timestamp deployment_timestamp) {
		this.deployment_timestamp = deployment_timestamp;
	}

	public SystemPersonnel getTesting_person() {
		return testing_person;
	}

	public void setTesting_person(SystemPersonnel testing_person) {
		this.testing_person = testing_person;
	}

	public SystemPersonnel getDeployment_person() {
		return deployment_person;
	}

	public void setDeployment_person(SystemPersonnel deployment_person) {
		this.deployment_person = deployment_person;
	}

	public String getWar_file_1() {
		return war_file_1;
	}

	public void setWar_file_1(String war_file_1) {
		this.war_file_1 = war_file_1;
	}

	public Integer getWar_file_1_svn_revision() {
		return war_file_1_svn_revision;
	}

	public void setWar_file_1_svn_revision(Integer war_file_1_svn_revision) {
		this.war_file_1_svn_revision = war_file_1_svn_revision;
	}

	public String getWar_file_2() {
		return war_file_2;
	}

	public void setWar_file_2(String war_file_2) {
		this.war_file_2 = war_file_2;
	}

	public Integer getWar_file_2_svn_revision() {
		return war_file_2_svn_revision;
	}

	public void setWar_file_2_svn_revision(Integer war_file_2_svn_revision) {
		this.war_file_2_svn_revision = war_file_2_svn_revision;
	}

	public String getWar_file_3() {
		return war_file_3;
	}

	public void setWar_file_3(String war_file_3) {
		this.war_file_3 = war_file_3;
	}

	public Integer getWar_file_3_svn_revision() {
		return war_file_3_svn_revision;
	}

	public void setWar_file_3_svn_revision(Integer war_file_3_svn_revision) {
		this.war_file_3_svn_revision = war_file_3_svn_revision;
	}

	public String getWar_file_4() {
		return war_file_4;
	}

	public void setWar_file_4(String war_file_4) {
		this.war_file_4 = war_file_4;
	}

	public Integer getWar_file_4_svn_revision() {
		return war_file_4_svn_revision;
	}

	public void setWar_file_4_svn_revision(Integer war_file_4_svn_revision) {
		this.war_file_4_svn_revision = war_file_4_svn_revision;
	}

	public String getWar_file_5() {
		return war_file_5;
	}

	public void setWar_file_5(String war_file_5) {
		this.war_file_5 = war_file_5;
	}

	public Integer getWar_file_5_svn_revision() {
		return war_file_5_svn_revision;
	}

	public void setWar_file_5_svn_revision(Integer war_file_5_svn_revision) {
		this.war_file_5_svn_revision = war_file_5_svn_revision;
	}

	public String getWar_file_6() {
		return war_file_6;
	}

	public void setWar_file_6(String war_file_6) {
		this.war_file_6 = war_file_6;
	}

	public Integer getWar_file_6_svn_revision() {
		return war_file_6_svn_revision;
	}

	public void setWar_file_6_svn_revision(Integer war_file_6_svn_revision) {
		this.war_file_6_svn_revision = war_file_6_svn_revision;
	}

	public String getSvn_tag_name() {
		return svn_tag_name;
	}

	public void setSvn_tag_name(String svn_tag_name) {
		this.svn_tag_name = svn_tag_name;
	}

	public Character getIs_tested() {
		return is_tested;
	}

	public void setIs_tested(Character is_tested) {
		this.is_tested = is_tested;
	}

	public int getMajor_version() {
		return major_version;
	}

	public void setMajor_version(int major_version) {
		this.major_version = major_version;
	}

	public int getMinor_version() {
		return minor_version;
	}

	public void setMinor_version(int minor_version) {
		this.minor_version = minor_version;
	}

	public int getPatch_version() {
		return patch_version;
	}

	public void setPatch_version(int patch_version) {
		this.patch_version = patch_version;
	}

	public String getMajor_bug_fixes() {
		return major_bug_fixes;
	}

	public void setMajor_bug_fixes(String major_bug_fixes) {
		this.major_bug_fixes = major_bug_fixes;
	}

	public String getMinor_bug_fixes() {
		return minor_bug_fixes;
	}

	public void setMinor_bug_fixes(String minor_bug_fixes) {
		this.minor_bug_fixes = minor_bug_fixes;
	}

	public String getPatch_bug_fixes() {
		return patch_bug_fixes;
	}

	public void setPatch_bug_fixes(String patch_bug_fixes) {
		this.patch_bug_fixes = patch_bug_fixes;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}

	public Character getRolled_back() {
		return rolled_back;
	}

	public void setRolled_back(Character rolled_back) {
		this.rolled_back = rolled_back;
	}

	public String getRollback_version() {
		return rollback_version;
	}

	public void setRollback_version(String rollback_version) {
		this.rollback_version = rollback_version;
	}
	
}
