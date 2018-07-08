package com.tring.customer.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CustomerLogin")
public class CustomerLogin {
	@Id
	@Column(name = "CustomerLoginId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int CustomerLoginId;
	@Column(name = "CustomerId", length = 45)
	protected String customerId;
	@Column(name = "PasswordHash", length = 45)
	protected String passwordHash;
	@Column(name = "PasswordSalt", length = 45)
	protected String passwordSalt;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Customer_CustomerId", nullable = false, unique = true)
	Customer customer;

	public int getCustomerLoginId() {
		return CustomerLoginId;
	}

	public void setCustomerLoginId(int customerLoginId) {
		CustomerLoginId = customerLoginId;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((passwordHash == null) ? 0 : passwordHash.hashCode());
		result = prime * result + ((passwordSalt == null) ? 0 : passwordSalt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerLogin other = (CustomerLogin) obj;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (passwordHash == null) {
			if (other.passwordHash != null)
				return false;
		} else if (!passwordHash.equals(other.passwordHash))
			return false;
		if (passwordSalt == null) {
			if (other.passwordSalt != null)
				return false;
		} else if (!passwordSalt.equals(other.passwordSalt))
			return false;
		return true;
	}

}
