package com.onlineretailstore.shopping.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.onlineretailstore.shopping.entity.Customer;

@FeignClient(name = "customer", url = "http://localhost:8081/")

public interface FeignCustomer {

	@GetMapping("api/customer/searchCustomer/{customerId}")
	public ResponseEntity<Customer> searchCustomer(@PathVariable Integer customerId);

	@PostMapping("api/customer/addCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer);

	@PutMapping("api/customer/updateCustomer/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer);

	@DeleteMapping("api/customer/deleteCustomer/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId);

}
