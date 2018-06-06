package com.telappoint.commonrestws.common.masterdb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.common.masterdb.dao.AdminLoginConfigDAO;
import com.telappoint.commonrestws.common.masterdb.domain.AdminLoginConfig;
import com.telappoint.commonrestws.common.model.to.ResetPasswordTO;

@Repository
public class AdminLoginConfigDAOImpl extends BaseDao<AdminLoginConfig, Integer> implements AdminLoginConfigDAO {

	@Override
	public String getPasswordComplexityLogic(String clientCode) {
		String passwordComplexity = "";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select id,client_id,password_complexity from admin_login_config where client_id in ");
		stringBuilder.append("  (select id from client where client_code='");
		stringBuilder.append(clientCode);
		stringBuilder.append("'); ");
		//System.out.println(" Query  --------------------> "+stringBuilder.toString());
		List<Object[]> nativeQueryList = findByNativeQuery(stringBuilder.toString());
		if(nativeQueryList!=null && nativeQueryList.size()>0){
			Object[] objArray = nativeQueryList.get(0);
			passwordComplexity = (String)objArray[2];
		}
		//System.out.println(" passwordComplexity  ---------------> "+passwordComplexity); 
		return passwordComplexity;
	}
	
	@Override
	public String getPasswordComplexityLogicByUserName(String username) {
		String passwordComplexity = "";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" select id,client_id,password_complexity from admin_login_config where client_id in ");
		stringBuilder.append("  (select client_id from admin_login_new where username='");
		stringBuilder.append(username);
		stringBuilder.append("'); ");
		System.out.println(" Query  --------------------> "+stringBuilder.toString());
		List<Object[]> nativeQueryList = findByNativeQuery(stringBuilder.toString());
		if(nativeQueryList!=null && nativeQueryList.size()>0){
			Object[] objArray = nativeQueryList.get(0);
			passwordComplexity = (String)objArray[2];
		}
		System.out.println(" passwordComplexity  ---------------> "+passwordComplexity); 
		return passwordComplexity;
	}
	
	
	@Override
	public ResetPasswordTO getPasswordComplexityLogic(int adminLoginNewIdId) {
		ResetPasswordTO resetPasswordTO = null;
		StringBuilder stringBuilder = new StringBuilder();
		//select aln.username , alc.password_complexity , c.client_code from admin_login_new aln, admin_login_config alc ,client c 	where alc.client_id=aln.client_id and aln.client_id=c.id and aln.id=1
		stringBuilder.append(" select aln.username , alc.password_complexity , c.client_code ,alc.password_reset_algorithm ");
		stringBuilder.append(" from admin_login_new aln, admin_login_config alc ,client c  ");
		stringBuilder.append(" where alc.client_id=aln.client_id and aln.client_id=c.id and aln.id=");
		stringBuilder.append(adminLoginNewIdId);
		//System.out.println(" Query  --------------------> "+stringBuilder.toString());
		List<Object[]> nativeQueryList = findByNativeQuery(stringBuilder.toString());
		if(nativeQueryList!=null && nativeQueryList.size()>0){
			Object[] objArray = nativeQueryList.get(0);
			String userName = (String)objArray[0];
			String password_complexity = (String)objArray[1];
			String client_code = (String)objArray[2];
			String password_reset_algorithm = (String)objArray[3];
			if(userName!="" && userName!=null && password_complexity!="" && password_complexity!=null && client_code!="" && client_code!=null){				
				resetPasswordTO = new ResetPasswordTO();
				resetPasswordTO.setClientCode(client_code);
				resetPasswordTO.setUserName(userName);
				resetPasswordTO.setPasswordComplexity(password_complexity);
				resetPasswordTO.setPasswordResetAlgorithm(password_reset_algorithm);
			}
			
		}
		/*System.out.println(" client_code  ---------------> "+resetPasswordTO.getClientCode());
		System.out.println(" userName  ---------------> "+resetPasswordTO.getUserName());
		System.out.println(" passwordComplexity  ---------------> "+resetPasswordTO.getPasswordComplexity());*/
		return resetPasswordTO;
	}
	
}
