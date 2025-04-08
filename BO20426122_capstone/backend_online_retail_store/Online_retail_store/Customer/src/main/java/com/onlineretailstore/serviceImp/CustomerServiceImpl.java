package com.onlineretailstore.serviceImp;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineretailstore.entity.Customer;
import com.onlineretailstore.entity.CustomerAddress;
import com.onlineretailstore.exception.CustomerNotFoundException;
import com.onlineretailstore.exception.InvalidCustomerDataException;
import com.onlineretailstore.repository.CustomerAddressRepository;
import com.onlineretailstore.repository.CustomerRepository;
import com.onlineretailstore.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerAddressRepository customerAddressRepo;

    @Override
    public Customer addCustomer(Customer customer) throws InvalidCustomerDataException {
        try {
            if (customer.getCustomerName() == null || customer.getCustomerEmail() == null) {
                logger.warn("Invalid customer data for: {}", customer);
                throw new InvalidCustomerDataException("Customer name and email must not be null.");
            }

            logger.info("Adding customer with name: {}", customer.getCustomerName());
            Customer savedCustomer = customerRepository.save(customer);
            logger.info("Customer added with ID: {}", savedCustomer.getCustomerId());
            return savedCustomer;
        } catch (InvalidCustomerDataException e) {
            logger.error("Invalid customer data provided: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while adding customer: {}", e.getMessage());
            throw new RuntimeException("Error adding customer", e);
        }
    }

    @Override
    public void deleteCustomer(int customerId) throws CustomerNotFoundException {
        try {
            logger.info("Deleting customer with ID: {}", customerId);
            if (!customerRepository.existsById(customerId)) {
                logger.warn("Customer with ID: {} not found for deletion", customerId);
                throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
            }

            customerRepository.deleteById(customerId);
            customerAddressRepo.deleteById(customerId);  // Assuming this deletes address with the same ID
            logger.info("Customer with ID: {} deleted successfully", customerId);
        } catch (CustomerNotFoundException e) {
            logger.error("Customer not found: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while deleting customer: {}", e.getMessage());
            throw new RuntimeException("Error deleting customer", e);
        }
    }

    @Override
    public Customer updateCustomer(int customerId, Customer updatedCustomer) 
            throws CustomerNotFoundException, InvalidCustomerDataException {
        try {
            if (updatedCustomer.getCustomerName() == null || updatedCustomer.getCustomerEmail() == null) {
                logger.warn("Invalid customer data for update: {}", updatedCustomer);
                throw new InvalidCustomerDataException("Customer name and email must not be null.");
            }

            logger.info("Updating customer with ID: {}", customerId);
            Optional<Customer> customerOpt = customerRepository.findById(customerId);

            if (customerOpt.isPresent()) {
                Customer customer = customerOpt.get();
                customer.setCustomerName(updatedCustomer.getCustomerName());
                customer.setCustomerEmail(updatedCustomer.getCustomerEmail());
                customer.setCustomerBillingAddress(updatedCustomer.getCustomerBillingAddress());
                customer.setCustomerShippingAddress(updatedCustomer.getCustomerShippingAddress());

                Customer savedCustomer = customerRepository.save(customer);
                logger.info("Customer with ID: {} updated successfully", savedCustomer.getCustomerId());
                return savedCustomer;
            } else {
                logger.warn("Customer with ID: {} not found for update", customerId);
                throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
            }
        } catch (CustomerNotFoundException | InvalidCustomerDataException e) {
            logger.error("Error during customer update: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while updating customer: {}", e.getMessage());
            throw new RuntimeException("Error updating customer", e);
        }
    }

    @Override
    public Optional<Customer> searchCustomer(int customerId) throws CustomerNotFoundException {
        try {
            logger.info("Searching for customer with ID: {}", customerId);
            Optional<Customer> customerOpt = customerRepository.findById(customerId);

            if (customerOpt.isPresent()) {
                logger.info("Customer found with ID: {}", customerId);
                return customerOpt;
            } else {
                logger.warn("Customer with ID: {} not found", customerId);
                throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
            }
        } catch (CustomerNotFoundException e) {
            logger.error("Customer not found: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while searching for customer: {}", e.getMessage());
            throw new RuntimeException("Error searching for customer", e);
        }
    }

    @Override
    public List<CustomerAddress> searchCustomerAddress(int customerId) throws CustomerNotFoundException {
        try {
            logger.info("Searching for addresses of customer with ID: {}", customerId);
            Optional<Customer> customerOpt = customerRepository.findById(customerId);

            if (customerOpt.isPresent()) {
                List<CustomerAddress> addresses = customerOpt.get().getCustomerBillingAddress();
                logger.info("Addresses found for customer with ID: {}", customerId);
                return addresses;
            } else {
                logger.warn("Customer with ID: {} not found when searching for addresses", customerId);
                throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
            }
        } catch (CustomerNotFoundException e) {
            logger.error("Customer not found: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while searching for customer addresses: {}", e.getMessage());
            throw new RuntimeException("Error searching for customer addresses", e);
        }
    }
}
