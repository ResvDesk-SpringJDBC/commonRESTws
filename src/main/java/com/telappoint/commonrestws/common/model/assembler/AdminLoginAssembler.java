package com.telappoint.commonrestws.common.model.assembler;

import java.text.DateFormat;
import com.telappoint.commonrestws.common.constants.CommonDateContants;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginNew;
import com.telappoint.commonrestws.common.model.to.AdminInfoTO;
import com.telappoint.commonrestws.common.utils.DateUtils;

public class AdminLoginAssembler {
	private static ThreadLocal<DateFormat> tltimestamp = DateUtils.getSimpleDateFormat(CommonDateContants.DATETIME_FORMAT_YYYYMMDDHHMMSS_CAP.getValue());
	public static AdminInfoTO getAdminInfoTO(AdminLoginNew adminLogin) {

		AdminInfoTO adminInfoTO = new AdminInfoTO();
		if (adminLogin.getId() != 0) {
			adminInfoTO.setId(adminLogin.getId());
		}
		adminInfoTO.setAccessLevel(adminLogin.getAccess_level());
		adminInfoTO.setContactEmail(adminLogin.getContact_email());
		adminInfoTO.setContactPhone(adminLogin.getContact_phone());
		adminInfoTO.setExpireDate(tltimestamp.get().format(adminLogin.getExpire_date()));
		adminInfoTO.setFirstName(adminLogin.getFirst_name());
		adminInfoTO.setLastName(adminLogin.getLast_name());
		adminInfoTO.setLocationIds(adminLogin.getResource_ids());
		adminInfoTO.setMasterPassword(adminLogin.getPassword());
		adminInfoTO.setPassword(adminLogin.getPassword());
		adminInfoTO.setResourceIds(adminLogin.getResource_ids());
		adminInfoTO.setStartDate(tltimestamp.get().format(adminLogin.getStart_date()));
		adminInfoTO.setUserName(adminLogin.getUsername());
		return adminInfoTO;
	}

	public static AdminLoginNew getAdmin_login(AdminInfoTO adminInfoTO) {
		AdminLoginNew admin_login = new AdminLoginNew();
		if (adminInfoTO.getId() != 0) {
			admin_login.setId(adminInfoTO.getId());
		}
		admin_login.setUsername(adminInfoTO.getUserName());
		admin_login.setStart_date(DateUtils.getTimestampFromString(adminInfoTO.getStartDate()));
		admin_login.setResource_ids(adminInfoTO.getResourceIds());
		admin_login.setPassword(adminInfoTO.getPassword());
		admin_login.setLocation_ids(adminInfoTO.getLocationIds());
		admin_login.setLast_name(adminInfoTO.getLastName());
		admin_login.setFirst_name(adminInfoTO.getFirstName());
		admin_login.setExpire_date(DateUtils.getTimestampFromString(adminInfoTO.getExpireDate()));
		admin_login.setContact_phone(adminInfoTO.getContactPhone());
		admin_login.setContact_email(adminInfoTO.getContactEmail());
		admin_login.setAccess_level(adminInfoTO.getAccessLevel());
		return admin_login;
	}

}
