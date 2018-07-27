package com.tring.customer.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlType
@XmlRootElement(name = "CustomerLogin")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class CustomerLoginDto {
	@XmlElement(name = "CustomerLoginId")
	@JsonProperty("CustomerLoginId")
	protected int customerLoginId;

	@XmlElement(name = "CustomerId")
	@JsonProperty("CustomerId")
	protected String customerId;

	@XmlElement(name = "PasswordHash")
	@JsonProperty("PasswordHash")
	protected String passwordHash;

	@XmlElement(name = "PasswordSalt")
	@JsonProperty("PasswordSalt")
	protected String passwordSalt;

	@XmlElement(name="Customer")
	@JsonProperty("Customer")
	CustomerDto customer;

	public int getCustomerLoginId() {
		return customerLoginId;
	}

	public void setCustomerLoginId(int customerLoginId) {
		this.customerLoginId = customerLoginId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	

}
