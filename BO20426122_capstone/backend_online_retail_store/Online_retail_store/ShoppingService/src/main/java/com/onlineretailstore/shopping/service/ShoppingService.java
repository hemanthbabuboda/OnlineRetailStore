package com.onlineretailstore.shopping.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.onlineretailstore.shopping.entity.Cart;
import com.onlineretailstore.shopping.entity.CompleteCustomerOrderDetails;
import com.onlineretailstore.shopping.entity.Customer;
import com.onlineretailstore.shopping.entity.Order;
import com.onlineretailstore.shopping.entity.ProductWrapper;
import com.onlineretailstore.shopping.exception.BadRequestException;
import com.onlineretailstore.shopping.exception.ResourceNotFoundException;

public interface ShoppingService {

	ResponseEntity<?> addProduct(@RequestBody ProductWrapper product) throws BadRequestException;

	ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws BadRequestException;

	ResponseEntity<Cart> addItemsToCart(@PathVariable Integer customerId, @RequestBody Cart cart)
			throws ResourceNotFoundException, BadRequestException;

	ResponseEntity<Order> order(@PathVariable Integer customerId) throws ResourceNotFoundException, BadRequestException;

	ResponseEntity<CompleteCustomerOrderDetails> getAllOrders(@PathVariable Integer customerId)
			throws ResourceNotFoundException, BadRequestException;
}
