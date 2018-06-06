/**
 * 
 */
package com.telappoint.commonrestws.common.dao.masterdb;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.telappoint.commonrestws.common.dao.BaseDAOTest;
import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginDAO;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLogin;

/**
 * 
 * @author Murali
 * 
 */
public class AdminLoginDAOTest extends BaseDAOTest {
	
	@Autowired
	private AdminLoginDAO adminLoginDAO;
	
	@Test 
	public void findAllAdminLoginTest() {
		 List<AdminLogin> list = adminLoginDAO.findAll();
		 System.out.println("list ----> "+list);
	}
}
