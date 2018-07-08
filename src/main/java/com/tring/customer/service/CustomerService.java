package com.tring.customer.service;

import java.util.List;

import com.tring.customer.dto.CustomerDto;

/**
 * @author akula
 *
 */
public interface CustomerService {

	List<CustomerDto> getAllCustomers();

	CustomerDto getCustomerById(int customerId);

	void updateCustomerById(CustomerDto customerDto);

	void deleteCustomerById(int customerId);
}
