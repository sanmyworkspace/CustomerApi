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
import com.tring.customer.mapper.CustomerMapper;
import com.tring.customer.model.Customer;
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
		List<Customer> customersList = customerDao.getAllCustomers();
		if (customersList != null && !customersList.isEmpty()) {
			customerDtosList = new ArrayList<CustomerDto>();
			for (Customer customer : customersList) {
				CustomerDto customerDto = customerMapper.mapCustomerToCustomerDto(customer);
				customerDtosList.add(customerDto);
			}
		}
		return customerDtosList;
	}

	@Override
	public CustomerDto getCustomerById(int customerId) {
		logger.debug("getCustomerById");
		Customer customer = customerDao.getCustomerById(customerId);
		CustomerDto customerDto = customerMapper.mapCustomerToCustomerDto(customer);
		return customerDto;
	}

	@Override
	public void updateCustomerById(CustomerDto customerDto) {
		logger.debug("updateCustomerById");
		Customer customer = customerMapper.mapCustomerDtoToCustomer(customerDto);
		customerDao.updateCustomerById(customer);
	}

	@Override
	public void deleteCustomerById(int customerId) {
		logger.debug("deleteCustomerById");
		Customer customer = customerDao.getCustomerById(customerId);
		customerDao.deleteCustomerById(customer);
	}

}