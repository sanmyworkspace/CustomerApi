package com.tring.customer.mapper;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tring.customer.dto.CustomerDto;
import com.tring.customer.dto.PersonalDetailsDto;
import com.tring.customer.model.Customer;
import com.tring.customer.model.PersonalDetails;

/**
 * @author akula
 *
 */
@Component
public class CustomerMapper {
	Logger logger = LogManager.getLogger(CustomerMapper.class);

	/**
	 * @param customerDto
	 * @return Customer object
	 */
	public Customer mapCustomerDtoToCustomer(CustomerDto customerDto) {
		logger.debug("mapCustomerDtoToCustomer");
		Customer customer = new Customer();
		customer.setCustomerId(customerDto.getCustomerId());
		customer.setFirstName(customerDto.getFirstName());
		customer.setLastName(customerDto.getLastName());
		customer.setAge(customerDto.getAge());
		customer.setSex(customerDto.getSex());
		customer.setUserName(customerDto.getUserName());

		PersonalDetailsDto personalDetailsDto = customerDto.getPersonalDetails();
		PersonalDetails personalDetails = new PersonalDetails();
		personalDetails.setPersonalDetailsId(personalDetailsDto.getPersonalDetailsId());
		personalDetails.setAddress1(personalDetailsDto.getAddress1());
		personalDetails.setAddress2(personalDetailsDto.getAddress2());
		personalDetails.setCity(personalDetailsDto.getCity());
		personalDetails.setState(personalDetailsDto.getState());
		personalDetails.setPincode(personalDetailsDto.getPincode());

		customer.setPersonalDetails(personalDetails);
		return customer;
	}

	/**
	 * @param customer
	 * @return CustomerDto
	 */
	public CustomerDto mapCustomerToCustomerDto(Customer customer) {
		logger.debug("mapCustomerToCustomerDto");
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(customer.getCustomerId());
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setAge(customer.getAge());
		customerDto.setSex(customer.getSex());
		customerDto.setUserName(customer.getUserName());
		
		PersonalDetailsDto personalDetailsDto = new PersonalDetailsDto();
		PersonalDetails personalDetails = customer.getPersonalDetails();
		personalDetailsDto.setPersonalDetailsId(personalDetails.getPersonalDetailsId());
		personalDetailsDto.setAddress1(personalDetails.getAddress1());
		personalDetailsDto.setAddress2(personalDetails.getAddress2());
		personalDetailsDto.setCity(personalDetails.getCity());
		personalDetailsDto.setState(personalDetails.getState());
		personalDetailsDto.setPincode(personalDetails.getPincode());
		
		customerDto.setPersonalDetails(personalDetailsDto);
		return customerDto;
	}
}
