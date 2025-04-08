package com.onlineretailstore.inventory.controller;

import java.time.Duration;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineretailstore.inventory.entity.Inventory;
import com.onlineretailstore.inventory.exception.InventoryNotFoundException;
import com.onlineretailstore.inventory.service.InventoryService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/**
 * The {@code InventoryController} class is a REST controller for managing
 * inventory-related operations. It provides endpoints for adding, updating,
 * deleting, and retrieving inventory items. This controller uses circuit
 * breaker patterns to enhance resilience.
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);

	@Autowired
	InventoryService inventoryService;

	/**
	 * Adds a new inventory item.
	 *
	 * @param inventory the inventory object to be added
	 * @return a ResponseEntity containing the created inventory and the HTTP status
	 */
	@CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackAddInventory")
	@PostMapping
	public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
		LocalDateTime startTime = LocalDateTime.now();
		logger.info("Entering addInventory method");
		try {
			Inventory createdInventory = inventoryService.addInventory(inventory);
			logger.info("Inventory added successfully: {}", createdInventory);
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of addInventory() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Error adding inventory: {}", e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of addInventory() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Deletes an inventory item by ID.
	 *
	 * @param id the ID of the inventory item to be deleted
	 * @return a ResponseEntity with HTTP status indicating the result of the
	 *         operation
	 */
	@CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackDeleteInventory")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInventory(@PathVariable Integer id) {
		LocalDateTime startTime = LocalDateTime.now();
		logger.info("Entering deleteInventory method");
		try {
			inventoryService.deleteInventory(id);
			logger.info("Inventory deleted successfully for ID: {}", id);
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of deleteInventory() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error deleting inventory for ID {}: {}", id, e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of deleteInventory() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Updates an existing inventory item.
	 *
	 * @param id        the ID of the inventory item to be updated
	 * @param inventory the updated inventory object
	 * @return a ResponseEntity containing the updated inventory and the HTTP status
	 */
	@CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackUpdateInventory")
	@PutMapping("/{id}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable Integer id, @RequestBody Inventory inventory) {
		LocalDateTime startTime = LocalDateTime.now();
		logger.info("Entering updateInventory method");
		try {
			Inventory updatedInventory = inventoryService.updateInventory(id, inventory);
			logger.info("Inventory updated successfully for ID: {}", id);
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of updateInventory() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(updatedInventory, HttpStatus.OK);
		} catch (InventoryNotFoundException e) {
			logger.warn("Inventory not found for ID {}: {}", id, e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of updateInventory() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Error updating inventory for ID {}: {}", id, e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of updateInventory() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieves an inventory item by ID.
	 *
	 * @param id the ID of the inventory item to retrieve
	 * @return a ResponseEntity containing the found inventory and the HTTP status
	 */
	@CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackGetInventory")
	@GetMapping("/{id}")
	public ResponseEntity<Inventory> getInventory(@PathVariable Integer id) {
		LocalDateTime startTime = LocalDateTime.now();
		logger.info("Entering getInventory method");
		try {
			Inventory inventory = inventoryService.getInventory(id);
			logger.info("Inventory retrieved successfully for ID: {}", id);
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of getInventory() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(inventory, HttpStatus.OK);
		} catch (InventoryNotFoundException e) {
			logger.warn("Inventory not found for ID {}: {}", id, e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of getInventory() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Error retrieving inventory for ID {}: {}", id, e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of getInventory() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Retrieves an inventory item by product ID.
	 *
	 * @param productId the product ID associated with the inventory item
	 * @return a ResponseEntity containing the found inventory and the HTTP status
	 */
	@CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackGetInventoryByProductId")
	@GetMapping("/product/{productId}")
	public ResponseEntity<Inventory> getInventoryByProductId(@PathVariable Integer productId) {
		LocalDateTime startTime = LocalDateTime.now();
		logger.info("Entering getInventoryByProductId method");
		try {
			Inventory inventory = inventoryService.getInventoryByProductId(productId);
			logger.info("Inventory retrieved successfully for Product ID: {}", productId);
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of getInventoryByProductId() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(inventory, HttpStatus.OK);
		} catch (InventoryNotFoundException e) {
			logger.warn("Inventory not found for Product ID {}: {}", productId, e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of getInventoryByProductId() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			logger.error("Error retrieving inventory for Product ID {}: {}", productId, e.getMessage());
			LocalDateTime endTime = LocalDateTime.now();
			Duration responseTime = Duration.between(startTime, endTime);
			logger.info("Response time of getInventoryByProductId() => " + responseTime.toMillis() + " ms");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Fallback methods
	public ResponseEntity<Inventory> fallbackAddInventory(RuntimeException e) {
		logger.error("Fallback method for adding inventory triggered: {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<Void> fallbackDeleteInventory(RuntimeException e) {
		logger.error("Fallback method for deleting inventory triggered: {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<Inventory> fallbackUpdateInventory(RuntimeException e) {
		logger.error("Fallback method for updating inventory triggered: {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<Inventory> fallbackGetInventory(RuntimeException e) {
		logger.error("Fallback method for getting inventory triggered: {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<Inventory> fallbackGetInventoryByProductId(RuntimeException e) {
		logger.error("Fallback method for getting inventory by product ID triggered: {}", e.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
