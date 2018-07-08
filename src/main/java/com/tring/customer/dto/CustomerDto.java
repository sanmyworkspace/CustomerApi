package com.tring.customer.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author akula
 *
 */
@XmlType(name="")
@XmlRootElement(name = "Customer")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class CustomerDto {
	@XmlElement(name = "CustomerId")
	@JsonProperty("CustomerId")
	protected int customerId;

	@XmlElement(name = "FirstName")
	@JsonProperty("FirstName")
	protected String firstName;

	@XmlElement(name = "LastName")
	@JsonProperty("LastName")
	protected String lastName;

	@XmlElement(name = "Age")
	@JsonProperty("Age")
	protected String age;

	@XmlElement(name = "Sex")
	@JsonProperty("Sex")
	protected String sex;

	@XmlElement(name = "Username")
	@JsonProperty("Username")
	protected String userName;

	@XmlElement(name = "PersonalDetails")
	@JsonProperty("PersonalDetails")
	PersonalDetailsDto personalDetails;

	
	
	public CustomerDto() {
		super();
	}

	public CustomerDto(int customerId, String firstName, String lastName, String age, String sex, String userName,
			PersonalDetailsDto personalDetailsDto) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.sex = sex;
		this.userName = userName;
		this.personalDetails = personalDetailsDto;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public PersonalDetailsDto getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetailsDto personalDetails) {
		this.personalDetails = personalDetails;
	}

	
	
}