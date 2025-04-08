package com.onlineretailstore.shopping.entity;

import java.util.Objects;

public class Address {

	private int addressId;
	private int doorNo;
	private String streetName;
	private String layout;
	private String city;
	private String pincode;

	public Address() {

	}

	public Address(int addressId, int doorNo, String streetName, String layout, String city, String pincode) {
		super();
		this.addressId = addressId;
		this.doorNo = doorNo;
		this.streetName = streetName;
		this.layout = layout;
		this.city = city;
		this.pincode = pincode;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(int doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", doorNo=" + doorNo + ", streetName=" + streetName + ", layout="
				+ layout + ", city=" + city + ", pincode=" + pincode + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressId, city, doorNo, layout, pincode, streetName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return addressId == other.addressId && Objects.equals(city, other.city) && doorNo == other.doorNo
				&& Objects.equals(layout, other.layout) && Objects.equals(pincode, other.pincode)
				&& Objects.equals(streetName, other.streetName);
	}

}
