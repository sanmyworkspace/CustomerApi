package com.tring.customer.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.tring.customer.CustomerApiApplication;
import com.tring.customer.dao.CustomerDao;
import com.tring.customer.model.Customer;
import com.tring.customer.model.PersonalDetails;

@RunWith(SpringJUnit4ClassRunner.class)
//@TestPropertySource("classpath:test-hibernate.properties")
@SpringBootTest
//@ContextConfiguration(classes=CustomerApiApplication.class)
@EnableTransactionManagement
public class CustomerDaoImplTest {
	
	@Autowired
	private CustomerDaoImpl customerDaoImpl;
	
	@Test
	@Transactional
	public void addCustomer() {
		Customer customer=new Customer(1, "sankar", "akula", "29", "male", "aka", new PersonalDetails(1, "address1", "address2", "city", "state", "522413"));
		Customer c=customerDaoImpl.getCustomerById(101);
		System.out.println(c.getAge());
	}
	

}
