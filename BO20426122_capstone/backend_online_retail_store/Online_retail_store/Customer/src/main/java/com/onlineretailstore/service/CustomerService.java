package com.onlineretailstore.service;

import java.util.List;
import java.util.Optional;

import com.onlineretailstore.entity.Customer;
import com.onlineretailstore.entity.CustomerAddress;
import com.onlineretailstore.exception.CustomerNotFoundException;
import com.onlineretailstore.exception.InvalidCustomerDataException;

public interface CustomerService {

    Customer addCustomer(Customer customer) throws InvalidCustomerDataException;

    void deleteCustomer(int customerId) throws CustomerNotFoundException;

    Customer updateCustomer(int customerId, Customer updatedCustomer) 
            throws CustomerNotFoundException, InvalidCustomerDataException;

    Optional<Customer> searchCustomer(int customerId) throws CustomerNotFoundException;

    List<CustomerAddress> searchCustomerAddress(int customerId) throws CustomerNotFoundException;
}
