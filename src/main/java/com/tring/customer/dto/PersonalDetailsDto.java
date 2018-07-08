package com.tring.customer.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlType
@XmlRootElement(name = "PersonalDetails")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class PersonalDetailsDto {
	@XmlElement(name = "PersonalDetailsId")
	@JsonProperty("PersonalDetailsId")
	protected int personalDetailsId;

	@XmlElement(name = "Address1")
	@JsonProperty("Address1")
	protected String address1;

	@XmlElement(name = "Address2")
	@JsonProperty("Address2")
	protected String address2;

	@XmlElement(name = "City")
	@JsonProperty("City")
	protected String city;

	@XmlElement(name = "State")
	@JsonProperty("State")
	protected String state;

	@XmlElement(name = "Pincode")
	@JsonProperty("Pincode")
	protected String pincode;

	public PersonalDetailsDto() {
		super();
	}

	public PersonalDetailsDto(int personalDetailsId, String address1, String address2, String city, String state,
			String pincode) {
		super();
		this.personalDetailsId = personalDetailsId;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public int getPersonalDetailsId() {
		return personalDetailsId;
	}

	public void setPersonalDetailsId(int personalDetailsId) {
		this.personalDetailsId = personalDetailsId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
