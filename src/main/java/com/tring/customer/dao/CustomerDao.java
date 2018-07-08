package com.tring.customer.dao;

import java.util.List;

import com.tring.customer.model.Customer;

/**
 * @author akula
 *
 */
public interface CustomerDao {

	List<Customer> getAllCustomers();

	Customer getCustomerById(int id);

	void updateCustomerById(Customer customer);

	void deleteCustomerById(Customer customer);
}
