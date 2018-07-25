package com.tring.customer.rest.resource;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.tring.customer.dto.CustomerDto;
import com.tring.customer.exception.CustomerApplicationException;
import com.tring.customer.rest.exception.CustomerResourceErrorCodes;
import com.tring.customer.rest.exception.CustomerResourceException;
import com.tring.customer.service.CustomerService;

/**
 * @author akula
 * 
 */
@Path("/customers")
public class CustomerManagementResource {
	Logger logger = LogManager.getLogger(CustomerManagementResource.class);

	@Autowired
	CustomerService customerService;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getAllCustomers() {
		logger.debug("getAllCustomers");
		try {
			List<CustomerDto> customerDtosList = customerService.getAllCustomers();
			GenericEntity<List<CustomerDto>> genericEntityList = new GenericEntity<List<CustomerDto>>(customerDtosList){
			};
			return Response.ok(genericEntityList).build();
		} catch (CustomerApplicationException ex) {
			logger.error(ex);
			throw new CustomerResourceException(ex.getErrorMessage(), ex.getErrorCode());
		}
	}

	@GET
	@Path("{customerId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getCustomerById(@PathParam("customerId") int custId) {
		logger.debug("getCustomerById");
		try {
			CustomerDto customerDto = customerService.getCustomerById(custId);
			return Response.ok(customerDto).build();
		} catch (CustomerApplicationException ex) {
			logger.error(ex);
			throw new CustomerResourceException(ex.getErrorMessage(), ex.getErrorCode());
		}
	}

	@PUT
	@Path("{customerId}")
	@Produces({ MediaType.TEXT_PLAIN })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response udpateCustomerById(@PathParam("customerId") int custId, CustomerDto customerDto) {
		logger.debug("updateCustomerById");
		try {
			customerService.updateCustomerById(customerDto);
			return Response.ok("Customer with id [ " + custId + " ] updated successfully").build();
		} catch (CustomerApplicationException ex) {
			logger.error(ex);
			throw new CustomerResourceException(ex.getErrorMessage(),ex.getErrorCode());
		}
	}

	@DELETE
	@Path("{customerId}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Response deleteCustomerById(@PathParam("customerId") int custId) {
		logger.debug("deleteCustomerById");
		try {
			customerService.deleteCustomerById(custId);
			return Response.ok("Customer with id [ " + custId + " ] deleted successfully").build();
		} catch (CustomerApplicationException ex) {
			logger.error(ex);
			throw new CustomerResourceException(ex.getErrorMessage(), ex.getErrorCode());
		}
	}
}
