package com.tring.customer.rest.resource;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tring.customer.app.CustomerApiApplication;
import com.tring.customer.dto.CustomerDto;
import com.tring.customer.dto.PersonalDetailsDto;
import com.tring.customer.rest.exception.CustomerResourceException;
import com.tring.customer.service.CustomerService;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes={CustomerApiApplication.class})
public class CustomerManagementResourceTest {

	private static final int customerId = 0;

	/*@Rule
	public ExpectedException thrown = ExpectedException.none();*/

	@MockBean
	CustomerService customerServiceImpl;

/*	@MockBean
	CustomerService customerServiceImplMock;*/
	
/*	@MockBean
	CustomerDao customerDaoImpl;*/
	
	// use inject mocks with spring boot versions lower than 1.4
	@SpyBean
	private CustomerManagementResource customerManagementResource;

	List<CustomerDto> customersList = null;

	CustomerDto customerDto=null;
	
	@Before
	public void setUp() {
		customersList = new ArrayList<CustomerDto>();
		customersList.add(new CustomerDto(101, "firstName1", "lastName1", "30", "male", "uname1",
				new PersonalDetailsDto(201, "address1", "address2", "city1", "state1", "pincode1")));
		customersList.add(new CustomerDto(102, "firstName2", "lastName2", "28", "female", "uname1",
				new PersonalDetailsDto(202, "address1", "address2", "city2", "state1", "pincode1")));
		customersList.add(new CustomerDto(103, "firstName3", "lastName3", "40", "male", "uname1",
				new PersonalDetailsDto(203, "address1", "address2", "city1", "state1", "pincode1")));
		customersList.add(new CustomerDto(104, "firstName4", "lastName4", "35", "male", "uname1",
				new PersonalDetailsDto(204, "address1", "address2", "city1", "state1", "pincode1")));
		customersList.add(new CustomerDto(105, "firstName5", "lastName5", "70", "male", "uname1",
				new PersonalDetailsDto(205, "address1", "address2", "city1", "state1", "pincode1")));
		
		customerDto=new CustomerDto(105, "firstName5", "lastName5", "70", "male", "uname1",
				new PersonalDetailsDto(205, "address1", "address2", "city1", "state1", "pincode1"));
	}

	@Test
	public void getCustomers() {
		when(customerServiceImpl.getAllCustomers()).thenReturn(customersList);
		Response result = customerManagementResource.getAllCustomers();
		List<CustomerDto> dtos = (List<CustomerDto>) result.getEntity();
		assertNotNull(dtos);
		assertEquals(customersList, dtos);
		assertThat(result, notNullValue());
		assertEquals(200, result.getStatus());
	}
	
	@Test(expected=CustomerResourceException.class)
	public void getCustomers_ThrowExceptionWhenNoCustomersFoundWithErrorCode_204(){
		when(customerServiceImpl.getAllCustomers()).thenThrow(new CustomerResourceException("not customers found", 204));
		Response response=customerManagementResource.getAllCustomers();
		assertEquals(204, response.getStatus());
		verify(customerServiceImpl,times(1)).getAllCustomers();
		verify(customerManagementResource, times(1)).getAllCustomers();
	}
	
	@Test(expected=CustomerResourceException.class)
	public void getCustomers_ThrowExceptionWhenFailedInGettingCustomers_InternalServerErrorCode_500(){
		when(customerServiceImpl.getAllCustomers()).thenThrow(new CustomerResourceException("failed in getting customers",500));
		Response response=customerManagementResource.getAllCustomers();
		assertEquals(500, response.getStatus());
		verify(customerServiceImpl, times(1)).getAllCustomers();
		verify(customerManagementResource,times(1)).getAllCustomers();
	}
	
	@Test
	public void getCustomerById(){
		when(customerServiceImpl.getCustomerById(customerId)).thenReturn(customerDto);
		Response response=customerManagementResource.getCustomerById(customerId);
		CustomerDto custDto=(CustomerDto)response.getEntity();
		assertNotNull(custDto);
		assertEquals(customerDto, custDto);
		verify(customerServiceImpl, times(1)).getCustomerById(customerId);
		verify(customerManagementResource,times(1)).getCustomerById(customerId);
	}

	@Test(expected=CustomerResourceException.class)
	public void getCustomerById_ThrowCustomerWhenRequestedCustomerNotFound_ErrorCode_204(){
		when(customerServiceImpl.getCustomerById(customerId)).thenThrow(new CustomerResourceException("no customer found with id",204));
		Response response=customerManagementResource.getCustomerById(customerId);
		assertEquals(204, response.getStatus());
		verify(customerServiceImpl,times(1)).getCustomerById(customerId);
		verify(customerManagementResource,times(1)).getCustomerById(customerId);
	}
	
	@Test
	public void updateCustomerById(){
		doNothing().when(customerServiceImpl).updateCustomerById(customerDto);
		Response response=customerManagementResource.udpateCustomerById(customerId, customerDto);
		assertEquals(200, response.getStatus());
		verify(customerServiceImpl,times(1)).updateCustomerById(customerDto);
		verify(customerManagementResource,times(1)).udpateCustomerById(customerId, customerDto);
	}
	
	@Test(expected=CustomerResourceException.class)
	public void updateCustomerById_ThrowExceptionWhenFailedInUpdating_InternalServerErrorCode_500(){
		doThrow(new CustomerResourceException("failed in updating",500)).when(customerServiceImpl).updateCustomerById(customerDto);
		Response response=customerManagementResource.udpateCustomerById(customerId, customerDto);
		assertEquals(500, response.getStatus());
		verify(customerServiceImpl,times(1)).updateCustomerById(customerDto);
		verify(customerManagementResource,times(1)).udpateCustomerById(customerId, customerDto);
	}
	
	@Test
	public void deleteCustomer_OnSuccessfulDeletionWillReturnStatusCode_200(){
		doNothing().when(customerServiceImpl).deleteCustomerById(customerId);
		Response response=customerManagementResource.deleteCustomerById(customerId);
		assertEquals(200, response.getStatus());
		verify(customerManagementResource,times(1)).deleteCustomerById(customerId);
	}
	
	@Test(expected=CustomerResourceException.class)
	public void deleteCustomer_OnDeletionFailureWillReturnStatusCode_500(){
		doThrow(new CustomerResourceException("failed in deleting customer", 500)).when(customerServiceImpl).deleteCustomerById(customerId);
		Response response=customerManagementResource.deleteCustomerById(customerId);
		assertEquals(500, response.getStatus());
		verify(customerManagementResource,times(1)).deleteCustomerById(customerId);
	}
}