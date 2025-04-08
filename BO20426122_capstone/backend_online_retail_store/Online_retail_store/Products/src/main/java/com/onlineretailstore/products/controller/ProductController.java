package com.onlineretailstore.products.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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

import com.onlineretailstore.products.entity.Product;
import com.onlineretailstore.products.exception.InvalidProductDataException;
import com.onlineretailstore.products.exception.ProductNotFoundException;
import com.onlineretailstore.products.serviceImpl.ProductServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;

/**
 * REST controller for managing products in the online retail store. Provides
 * endpoints to create, update, delete, and retrieve product information.
 */
@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductServiceImpl productService;

	/**
	 * Adds a new product to the store.
	 *
	 * @param product the product to be added
	 * @return ResponseEntity containing the created product and HTTP status
	 */
	@CircuitBreaker(name = "productService", fallbackMethod = "fallbackAddProduct")
	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
		LocalDateTime startTime = LocalDateTime.now();
		logger.info("Entering addProduct method");
		try {
			logger.info("Received request to add product: {}", product.getProductName());
			Product createdProduct = productService.addProduct(product);
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of addProduct() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
		} catch (InvalidProductDataException e) {
			logger.error("Invalid product data: {}", e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of addProduct() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("Error adding product: {}", e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of addProduct() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Deletes a product from the store by its ID.
	 *
	 * @param productId the ID of the product to be deleted
	 * @return ResponseEntity indicating the outcome of the delete operation
	 */
	@CircuitBreaker(name = "productService", fallbackMethod = "fallbackDeleteProduct")
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {
		LocalDateTime startTime = LocalDateTime.now();
		logger.info("Entering deleteProduct method");
		try {
			logger.info("Received request to delete product with ID: {}", productId);
			productService.deleteProduct(productId);
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of deleteProduct() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>("Product deleted successfully.", HttpStatus.NO_CONTENT);
		} catch (ProductNotFoundException e) {
			logger.error("Product not found: {}", e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of deleteProduct() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Error deleting product: {}", e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of deleteProduct() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>("Error deleting product.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Updates an existing product in the store.
	 *
	 * @param productId the ID of the product to be updated
	 * @param product   the updated product information
	 * @return ResponseEntity containing the updated product and HTTP status
	 */
	@CircuitBreaker(name = "productService", fallbackMethod = "fallbackUpdateProduct")
	@PutMapping("/updateProduct/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @Valid @RequestBody Product product) {
		LocalDateTime startTime = LocalDateTime.now();
		logger.info("Entering updateProduct method");
		try {
			logger.info("Received request to update product with ID: {}", productId);
			Product updatedProduct = productService.updateProduct(productId, product);
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of updateProduct() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
		} catch (ProductNotFoundException | InvalidProductDataException e) {
			logger.error("Error updating product: {}", e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of updateProduct() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.error("Unexpected error updating product: {}", e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of updateProduct() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieves a product by its ID.
	 *
	 * @param productId the ID of the product to retrieve
	 * @return ResponseEntity containing the requested product and HTTP status
	 */
	@CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetProduct")
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
		LocalDateTime startTime = LocalDateTime.now();
		logger.info("Entering getProduct method");
		try {
			logger.info("Received request to fetch product with ID: {}", productId);
			Product product = productService.getProduct(productId);
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of getProduct() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (ProductNotFoundException e) {
			logger.error("Product not found: {}", e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of getProduct() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Error fetching product: {}", e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of getProduct() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieves all products in the store.
	 *
	 * @return ResponseEntity containing a list of products and HTTP status
	 */
	@CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetAllProducts")
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		LocalDateTime startTime = LocalDateTime.now();
		logger.info("Entering getAllProducts method");
		try {
			logger.info("Received request to fetch all products");
			List<Product> products = productService.getAllProducts();
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of getAllProducts() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error fetching all products: {}", e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of getAllProducts() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Fallback methods
	public ResponseEntity<Product> fallbackAddProduct(RuntimeException e) {
		logger.error("Fallback method for adding product triggered: {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<String> fallbackDeleteProduct(RuntimeException e) {
		logger.error("Fallback method for deleting product triggered: {}", e.getMessage());
		return new ResponseEntity<>("Error deleting product.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<Product> fallbackUpdateProduct(RuntimeException e) {
		logger.error("Fallback method for updating product triggered: {}", e.getMessage());
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<Product> fallbackGetProduct(RuntimeException e) {
		logger.error("Fallback method for getting product triggered: {}", e.getMessage());
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<List<Product>> fallbackGetAllProducts(RuntimeException e) {
		logger.error("Fallback method for getting all products triggered: {}", e.getMessage());
		return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}