package com.tring.customer.dao.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.tring.customer.dao.CustomerDao;
import com.tring.customer.dao.util.BaseDao;
import com.tring.customer.model.Customer;

/**
 * @author akula
 *
 */
@Repository
public class CustomerDaoImpl extends BaseDao implements CustomerDao {

	Logger logger = LogManager.getLogger(CustomerDaoImpl.class);

	@Override
	public List<Customer> getAllCustomers() {
		logger.debug("getAllCustomers");
		List<Customer> customersList = getCurrentSession().createNamedQuery("Customer.GetAll", Customer.class)
				.getResultList();
		return customersList;

	}

	@Override
	public Customer getCustomerById(int customerId) {
		logger.debug("getCustomerById");
		return (Customer) getCurrentSession().get(Customer.class, customerId);
	}

	@Override
	public void updateCustomerById(Customer customer) {
		logger.debug("updateCustomerById");
		getCurrentSession().update(customer);
	}

	@Override
	public void deleteCustomerById(Customer customer) {
		logger.debug("deleteCustomerById");
		getCurrentSession().delete(customer);
	}

}
