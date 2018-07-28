package com.tring.customer.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tring.customer.app.CustomerApiApplication;
import com.tring.customer.dao.CustomerDao;
import com.tring.customer.dto.CustomerDto;
import com.tring.customer.dto.PersonalDetailsDto;
import com.tring.customer.model.Customer;
import com.tring.customer.model.PersonalDetails;
import com.tring.customer.service.CustomerService;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CustomerApiApplication.class })
public class CustomerServiceImplTest {

	private static final int customerId = 1;

	@MockBean
	CustomerDao customerDao;

	@Autowired
	CustomerService customerServiceImpl;

	private Customer customer;

	private PersonalDetails pd;

	private CustomerDto customerDto;

	private PersonalDetailsDto pdto;

	private Customer customerWithData;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		customer = new Customer();
		customerWithData = new Customer(101, "firstName", "lastName", "29", "male", "uname", new PersonalDetails());
		pd = new PersonalDetails();
		customer.setPersonalDetails(pd);
		customerDto = new CustomerDto();
		pdto = new PersonalDetailsDto();
		customerDto.setPersonalDetails(pdto);
	}

	@Test
	public void getAllCustomers_NotNull() {
		when(customerDao.getAllCustomers()).thenReturn(Arrays.asList(customer));
		List<CustomerDto> customerDtos = customerServiceImpl.getAllCustomers();
		assertNotNull(customerDtos);
		verify(customerDao, times(1)).getAllCustomers();
	}

	@Test
	public void getAllCustomers_NotNullAndIsNotEmpty() {
		when(customerDao.getAllCustomers()).thenReturn(Arrays.asList(customerWithData));
		List<CustomerDto> customerDtos = customerServiceImpl.getAllCustomers();
		assertNotNull(customerDtos);
		assertTrue(!customerDtos.isEmpty());
		verify(customerDao, times(1)).getAllCustomers();
	}

	@Test
	public void getCustomerById_NotNullChecking() {
		when(customerDao.getCustomerById(customerId)).thenReturn(customer);
		CustomerDto customerDto = customerServiceImpl.getCustomerById(customerId);
		assertNotNull(customerDto);
		verify(customerDao, times(1)).getCustomerById(customerId);
	}

	@Test
	public void deleteCustomerById_success() {
		doNothing().when(customerDao).deleteCustomerById(customer);
		when(customerDao.getCustomerById(customerId)).thenReturn(customer);
		customerServiceImpl.deleteCustomerById(customerId);
		verify(customerDao, times(1)).getCustomerById(customerId);
	}

	@Test
	public void updateCustomerById_success() {
		doNothing().when(customerDao).updateCustomerById(customer);
		customerServiceImpl.updateCustomerById(customerDto);
		verify(customerDao, times(1)).updateCustomerById(customer);
	}

}
