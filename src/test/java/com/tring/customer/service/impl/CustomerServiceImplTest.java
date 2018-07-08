package com.tring.customer.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tring.customer.dao.CustomerDao;
import com.tring.customer.dto.CustomerDto;
import com.tring.customer.dto.PersonalDetailsDto;
import com.tring.customer.exception.CustomerApplicationException;
import com.tring.customer.model.Customer;
import com.tring.customer.model.PersonalDetails;
import com.tring.customer.service.CustomerService;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
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

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		customer = new Customer();
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

	@Test(expected = CustomerApplicationException.class)
	public void getAllCustomers_ThrowsExceptionWhenNoUserFound() {
		when(customerDao.getAllCustomers()).thenReturn(null);
		customerServiceImpl.getAllCustomers();
		verify(customerDao, times(1)).getAllCustomers();
	}

	@Test(expected = CustomerApplicationException.class)
	public void getAllCustomers_ThrowsFailureWhileFetchingFromDB() {
		when(customerDao.getAllCustomers()).thenThrow(new CustomerApplicationException());
		customerServiceImpl.getAllCustomers();
		verify(customerDao, times(1)).getAllCustomers();
	}

	@Test
	public void getCustomerById_NotNullChecking() {
		when(customerDao.getCustomerById(customerId)).thenReturn(customer);
		CustomerDto customerDto = customerServiceImpl.getCustomerById(customerId);
		assertNotNull(customerDto);
		verify(customerDao, times(1)).getCustomerById(customerId);
	}

	@Test(expected = CustomerApplicationException.class)
	public void getCustomerById_NoCustomerFoundThenThrowCustomerApplicationException() {
		when(customerDao.getCustomerById(customerId)).thenReturn(null);
		customerServiceImpl.getCustomerById(customerId);
		verify(customerDao, times(1)).getAllCustomers();
	}

	@Test(expected = CustomerApplicationException.class)
	public void getCustomerById_DaoFailedInFetchingDataThenThrowCustomerApplicaitonException() {
		when(customerDao.getCustomerById(customerId)).thenThrow(new CustomerApplicationException());
		customerServiceImpl.getCustomerById(customerId);
		verify(customerDao, times(1)).getCustomerById(customerId);
	}

	@Test
	public void deleteCustomerById_success() {
		doNothing().when(customerDao).deleteCustomerById(customer);
		when(customerDao.getCustomerById(customerId)).thenReturn(customer);
		customerServiceImpl.deleteCustomerById(customerId);
		verify(customerDao, times(1)).getCustomerById(customerId);
	}

	@Test(expected = CustomerApplicationException.class)
	public void deletCustomerById_ThrowCustomerApplicationExceptionWhenThereIsNotCustomerWithThatId() {
		doThrow(new CustomerApplicationException()).when(customerDao).deleteCustomerById(customer);
		when(customerDao.getCustomerById(customerId)).thenReturn(null);
		customerServiceImpl.deleteCustomerById(customerId);
		verify(customerDao, times(1)).deleteCustomerById(customer);
		verify(customerDao, times(1)).getCustomerById(customerId);
	}

	@Test(expected = CustomerApplicationException.class)
	public void deletCustomerById_ThrowCustomerApplicationExceptionWhenDatabaseFailed() {
		doNothing().when(customerDao).deleteCustomerById(customer);
		when(customerDao.getCustomerById(customerId)).thenThrow(new CustomerApplicationException());
		customerServiceImpl.deleteCustomerById(customerId);
		verify(customerDao, times(1)).deleteCustomerById(customer);
		verify(customerDao, times(1)).getCustomerById(customerId);
	}

	@Test
	public void updateCustomerById_success() {
		doNothing().when(customerDao).updateCustomerById(customer);
		customerServiceImpl.updateCustomerById(customerDto);
		verify(customerDao, times(1)).updateCustomerById(customer);
	}
	
	@Test(expected=CustomerApplicationException.class)
	public void updateCustomerById_FailedWhileUpdatingDatabase(){
		doThrow(new CustomerApplicationException()).when(customerDao).updateCustomerById(customer);
		customerServiceImpl.updateCustomerById(customerDto);
		verify(customerDao, times(1)).updateCustomerById(customer);
	}
}
