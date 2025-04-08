package com.onlineretailstore.service;


import com.onlineretailstore.entity.CustomerAddress;
import com.onlineretailstore.exception.AddressNotFoundException;
import com.onlineretailstore.exception.InvalidAddressDataException;

public interface CustomerAddressService {

	public CustomerAddress addCustomerAddress(CustomerAddress address)throws InvalidAddressDataException;
	public void deleteCustomerAddress(int addressId) throws AddressNotFoundException;
	public CustomerAddress updateCustomerAddress(int addressId, CustomerAddress address) throws AddressNotFoundException, InvalidAddressDataException ;
	public CustomerAddress searchCustomerAddress(int addressId)  throws AddressNotFoundException;
}
