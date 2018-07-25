package com.tring.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tring.customer.dao.CustomerDao;
import com.tring.customer.dto.CustomerDto;
import com.tring.customer.exception.CustomerApplicationException;
import com.tring.customer.mapper.CustomerMapper;
import com.tring.customer.model.Customer;
import com.tring.customer.rest.exception.CustomerResourceErrorCodes;
import com.tring.customer.service.CustomerService;

/**
 * @author akula
 *
 */
@Service
@Transactional("customerTxManager")
public class CustomerServiceImpl implements CustomerService {

	Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

	@Autowired
	CustomerDao customerDao;

	@Autowired
	CustomerMapper customerMapper;

	@Override
	public List<CustomerDto> getAllCustomers() {
		logger.debug("getAllCustomers");
		List<CustomerDto> customerDtosList = null;
		List<Customer> customersList = null;
		try {
			customersList = customerDao.getAllCustomers();
		} catch (Exception ex) {
			logger.error(ex);
			throw new CustomerApplicationException("Failed while getting list of customers..." + ex.getMessage(), CustomerResourceErrorCodes.INTERNAL_SERVER_ERROR);
		}
		if (customersList != null && !customersList.isEmpty()) {
			customerDtosList = new ArrayList<CustomerDto>();
			for (Customer customer : customersList) {
				CustomerDto customerDto = customerMapper.mapCustomerToCustomerDto(customer);
				customerDtosList.add(customerDto);
			}
		} else {
			throw new CustomerApplicationException("No customers found", CustomerResourceErrorCodes.NO_CONTENT);
		}
		return customerDtosList;
	}

	@Override
	public CustomerDto getCustomerById(int customerId) {
		logger.debug("getCustomerById");
		Customer customer = null;
		try {
			customer = customerDao.getCustomerById(customerId);
		} catch (Exception ex) {
			logger.error(ex);
			throw new CustomerApplicationException("Failed in getting customer with id: " + customerId, CustomerResourceErrorCodes.INTERNAL_SERVER_ERROR);
		}
		if (customer != null) {
			CustomerDto customerDto = customerMapper.mapCustomerToCustomerDto(customer);
			return customerDto;
		} else {
			throw new CustomerApplicationException("There is no customer exists with customer id: " + customerId, CustomerResourceErrorCodes.NO_CONTENT);
		}
	}

	@Override
	public void updateCustomerById(CustomerDto customerDto) {
		logger.debug("updateCustomerById");
		try {
			Customer customer = customerMapper.mapCustomerDtoToCustomer(customerDto);
			customerDao.updateCustomerById(customer);
		} catch (Exception ex) {
			logger.error(ex);
			throw new CustomerApplicationException("Failed in updating customer with id: " + customerDto.getCustomerId(), CustomerResourceErrorCodes.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void deleteCustomerById(int customerId) {
		logger.debug("deleteCustomerById");
		Customer customer = null;
		try {
			customer = customerDao.getCustomerById(customerId);
		} catch (Exception ex) {
			logger.error(ex);
			throw new CustomerApplicationException("Failed in deleting customer with id: " + customerId, CustomerResourceErrorCodes.INTERNAL_SERVER_ERROR);
		}
		if (customer != null) {
			customerDao.deleteCustomerById(customer);
		} else {
			throw new CustomerApplicationException("There is no customer exists with customer id: " + customerId, CustomerResourceErrorCodes.NO_CONTENT);
		}
	}

}