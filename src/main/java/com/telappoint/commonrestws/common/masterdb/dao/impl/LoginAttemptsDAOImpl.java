package com.telappoint.commonrestws.common.masterdb.dao.impl;

import org.springframework.stereotype.Repository;

import com.telappoint.commonrestws.common.dao.impl.BaseDao;
import com.telappoint.commonrestws.common.masterdb.dao.LoginAttemptsDAO;
import com.telappoint.commonrestws.common.masterdb.domain.LoginAttempts;

@Repository
public class LoginAttemptsDAOImpl extends BaseDao<LoginAttempts, Integer> implements LoginAttemptsDAO {

}
