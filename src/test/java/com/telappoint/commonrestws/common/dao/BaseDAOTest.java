package com.telappoint.commonrestws.common.dao;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Murali
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "/applicationContext.xml" })
public abstract class BaseDAOTest {
	private Logger logger = Logger.getLogger(BaseDAOTest.class);

	@Before
	public void setUpTestData() {
		logger.info("Testing Setup");
	}

	@After
	public void tearDown() {
		// execute "tear down" logic
		logger.info("Testing TearDown");
	}
}
