package com.tring.customer.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.tring.customer.rest.resource.CustomerManagementResourceTest;
import com.tring.customer.service.impl.CustomerServiceImplTest;

/**
 * @author aksankar
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ CustomerServiceImplTest.class, CustomerManagementResourceTest.class })
public class CustomerApiApplicationTestSuit {
	
}
