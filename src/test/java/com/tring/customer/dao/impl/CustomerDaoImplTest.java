package com.tring.customer.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.tring.customer.app.CustomerApiApplicationDBConfigTest;
import com.tring.customer.model.Customer;
import com.tring.customer.model.PersonalDetails;

/**
 * @author aksankar
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CustomerApiApplicationDBConfigTest.class })
@EnableTransactionManagement
@FixMethodOrder(MethodSorters.DEFAULT)
public class CustomerDaoImplTest {

	private static final int customerId = 1;

	@Autowired
	private CustomerDaoImpl customerDaoImpl;
	
	private Customer expectedCustomer;
	private PersonalDetails expectePersonalDetails;

	private PersonalDetails updatedPersonalDetails;

	private Customer updatedCustomer;
	
	
	@Before
	public void setUp() {
		expectePersonalDetails = new PersonalDetails(1, "address1", "address2", "city", "state", "pincode");
		expectedCustomer = new Customer(1, "sankar", "akula", "29", "male", "akula", expectePersonalDetails);
		customerDaoImpl.save(expectePersonalDetails);
		customerDaoImpl.save(expectedCustomer);
		
		updatedPersonalDetails = new PersonalDetails(1, "updated-address1", "address2", "city", "state", "pincode");
		updatedCustomer = new Customer(1, "sankar-updated", "akula", "29", "male", "akula", updatedPersonalDetails);
	}

	//@Test
	@Transactional
	public void getAllCustomers(){
		List<Customer> customersList=customerDaoImpl.getAllCustomers();
		assertNotNull(customersList);
	}
	
	//@Test
	@Transactional
	public void getCustomerById() {
		Customer customer = customerDaoImpl.getCustomerById(customerId);
		assertNotNull(customer);
		//assertEquals(expectedCustomer,customer);
	}
	
	@Test
	@Transactional
	public void updateCustomerById(){
		Customer customer=customerDaoImpl.getCustomerById(customerId);
		customer.setFirstName("sankar-updated");
		customerDaoImpl.updateCustomerById(customer);
		assertEquals("sankar-updated", customer.getFirstName());
		//assertEquals(customer,updatedCustomer);
	}
}
