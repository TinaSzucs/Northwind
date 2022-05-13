package com.sparta.northwind;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{
	@JsonProperty("country")
	private String country;

	@JsonProperty("contactTitle")
	private String contactTitle;

	@JsonProperty("address")
	private String address;

	@JsonProperty("city")
	private String city;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("contactName")
	private String contactName;

	@JsonProperty("companyName")
	private String companyName;

	@JsonProperty("postalCode")
	private String postalCode;

	@JsonProperty("id")
	private String id;

	@JsonProperty("region")
	private Object region;

	@JsonProperty("fax")
	private String fax;

	public String getCountry(){
		return country;
	}

	public String getContactTitle(){
		return contactTitle;
	}

	public String getAddress(){
		return address;
	}

	public String getCity(){
		return city;
	}

	public String getPhone(){
		return phone;
	}

	public String getContactName(){
		return contactName;
	}

	public String getCompanyName(){
		return companyName;
	}

	public String getPostalCode(){
		return postalCode;
	}

	public String getId(){
		return id;
	}

	public Object getRegion(){
		return region;
	}

	public String getFax(){
		return fax;
	}
}