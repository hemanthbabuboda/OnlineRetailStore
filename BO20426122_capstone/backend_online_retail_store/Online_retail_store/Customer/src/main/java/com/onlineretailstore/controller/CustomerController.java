package com.onlineretailstore.controller;

import java.time.Duration;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineretailstore.entity.Customer;
import com.onlineretailstore.exception.CustomerNotFoundException;
import com.onlineretailstore.exception.InvalidCustomerDataException;
import com.onlineretailstore.serviceImp.CustomerServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;

/**
 * The {@code CustomerController} class is a REST controller for managing
 * customer-related operations. It provides endpoints for adding, updating,
 * deleting, and retrieving customer information. This controller also
 * incorporates circuit breaker patterns for resilience.
 */
@RestController
@RequestMapping("/api/customer")
@Validated
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerServiceImpl customerService;

	/**
	 * Adds a new customer.
	 *
	 * @param customer the customer object to be added
	 * @return a ResponseEntity containing the created customer and the HTTP status
	 */
	@CircuitBreaker(name = "customerService", fallbackMethod = "fallbackAddCustomer")
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
		LocalDateTime startTime = LocalDateTime.now();
		logger.info("Entering addCustomer method");
		try {
			Customer createdCustomer = customerService.addCustomer(customer);
			logger.info("Customer added successfully: {}", createdCustomer);
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of addCustomer() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
		} catch (InvalidCustomerDataException e) {
			logger.error("Invalid customer data: {}", e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of addCustomer() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("Error adding customer: {}", e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of addCustomer() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Deletes a customer by ID.
	 *
	 * @param customerId the ID of the customer to be deleted
	 * @return a ResponseEntity with HTTP status indicating the result of the
	 *         operation
	 */
	@CircuitBreaker(name = "customerService", fallbackMethod = "fallbackDeleteCustomer")
	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) {
		LocalDateTime startTime = LocalDateTime.now();
		logger.info("Entering deleteCustomer method");
		try {
			customerService.deleteCustomer(customerId);
			logger.info("Customer deleted successfully for ID: {}", customerId);
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of deleteCustomer() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (CustomerNotFoundException e) {
			logger.warn("Customer not found for ID {}: {}", customerId, e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of deleteCustomer() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Error deleting customer for ID {}: {}", customerId, e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of deleteCustomer() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Updates an existing customer.
	 *
	 * @param customerId the ID of the customer to be updated
	 * @param customer   the updated customer object
	 * @return a ResponseEntity containing the updated customer and the HTTP status
	 */
	@CircuitBreaker(name = "customerService", fallbackMethod = "fallbackUpdateCustomer")
	@PutMapping("/updateCustomer/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int customerId,
			@Valid @RequestBody Customer customer) {
		LocalDateTime startTime = LocalDateTime.now();
		logger.info("Entering updateCustomer method");
		try {
			Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
			logger.info("Customer updated successfully for ID: {}", customerId);
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of updateCustomer() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
		} catch (CustomerNotFoundException e) {
			logger.warn("Customer not found for ID {}: {}", customerId, e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of updateCustomer() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (InvalidCustomerDataException e) {
			logger.error("Invalid customer data: {}", e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of updateCustomer() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("Error updating customer for ID {}: {}", customerId, e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of updateCustomer() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Searches for a customer by ID.
	 *
	 * @param customerId the ID of the customer to search for
	 * @return a ResponseEntity containing the found customer and the HTTP status
	 */
	@CircuitBreaker(name = "customerService", fallbackMethod = "fallbackSearchCustomer")
	@GetMapping("/searchCustomer/{customerId}")
	public ResponseEntity<Customer> searchCustomer(@Valid @PathVariable int customerId) {
		LocalDateTime startTime = LocalDateTime.now();
		logger.info("Entering searchCustomer method");
		try {
			Customer cust = customerService.searchCustomer(customerId)
					.orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));
			logger.info("Customer retrieved successfully for ID: {}", customerId);
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of searchCustomer() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(cust, HttpStatus.OK);
		} catch (CustomerNotFoundException e) {
			logger.warn("Customer not found for ID {}: {}", customerId, e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of searchCustomer() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Error retrieving customer for ID {}: {}", customerId, e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of searchCustomer() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Fallback methods
	public ResponseEntity<Customer> fallbackAddCustomer(RuntimeException e) {
		logger.error("Fallback method for adding customer triggered: {}", e.getMessage());
		return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
	}

	public ResponseEntity<Void> fallbackDeleteCustomer(RuntimeException e) {
		logger.error("Fallback method for deleting customer triggered: {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
	}

	public ResponseEntity<Customer> fallbackUpdateCustomer(RuntimeException e) {
		logger.error("Fallback method for updating customer triggered: {}", e.getMessage());
		return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
	}

	public ResponseEntity<Customer> fallbackSearchCustomer(RuntimeException e) {
		logger.error("Fallback method for searching customer triggered: {}", e.getMessage());
		return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
	}
}
