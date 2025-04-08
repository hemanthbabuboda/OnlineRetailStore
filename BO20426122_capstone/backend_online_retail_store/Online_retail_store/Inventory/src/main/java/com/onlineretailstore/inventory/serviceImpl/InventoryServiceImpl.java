package com.onlineretailstore.inventory.serviceImpl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.onlineretailstore.inventory.entity.Inventory;
import com.onlineretailstore.inventory.entity.Product;
import com.onlineretailstore.inventory.exception.InvalidInventoryDataException;
import com.onlineretailstore.inventory.exception.InventoryNotFoundException;
import com.onlineretailstore.inventory.repository.InventoryRepository;
import com.onlineretailstore.inventory.service.InventoryService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    private InventoryRepository inventoryRepository;
    
    @Autowired
    RestTemplateBuilder builder;

//    @CircuitBreaker(name = "inventoryService", fallbackMethod = "addInventoryFallback")
    public Inventory addInventory( Inventory inventory) {
        try {
            logger.info("Adding inventory: {}", inventory);
            Product product = getProductByInventoryId(inventory.getProductId());
            if(product.getProductId()==inventory.getProductId())
            {
//            	if(inventoryRepository.findByProductId(inventory.getProductId())==null)
//            		
//            	else
//            		throw new RuntimeException("inventory already exists, please update");
            	System.out.println(inventory);
            	return inventoryRepository.save(inventory);
            }
            else
            	throw new RuntimeException("product not found");
        } catch (Exception e) {
            logger.error("Error adding inventory: {}", e.getMessage());
            throw new InvalidInventoryDataException("Failed to add inventory: " + e.getMessage());
        }
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "deleteInventoryFallback")
    public void deleteInventory(@NotNull @Min(1) Integer productId) {
        try {
            logger.info("Deleting inventory with ID: {}", productId);
            Inventory inv = inventoryRepository.findByProductId(productId);
            if (inv==null) {
                logger.warn("Inventory with ID: {} not found for deletion", productId);
                throw new InventoryNotFoundException("Inventory not found with ID: " + productId);
            }
            inventoryRepository.deleteById(inv.getInventoryId());
            //logger.info("Inventory with ID: {} deleted successfully", productId);
        } catch (InventoryNotFoundException e) {
            logger.error("InventoryNotFoundException: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error deleting inventory: {}", e.getMessage());
            throw new RuntimeException("Failed to delete inventory: " + e.getMessage());
        }
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "updateInventoryFallback")
    public Inventory updateInventory(@NotNull @Min(1) Integer productId, @Valid Inventory inventory) {
        try {
            logger.info("Updating inventory with ID: {}", productId);
            Inventory existingInventory = getInventory(productId);
            existingInventory.setQuantity(inventory.getQuantity());
            Inventory updatedInventory = inventoryRepository.save(existingInventory);
            logger.info("Inventory with ID: {} updated successfully", updatedInventory.getInventoryId());
            return updatedInventory;
        } catch (InventoryNotFoundException | InvalidInventoryDataException e) {
            logger.error("Error updating inventory: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error updating inventory: {}", e.getMessage());
            throw new RuntimeException("Failed to update inventory: " + e.getMessage());
        }
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "getInventoryFallback")
    public Inventory getInventory(@NotNull @Min(1) Integer productId) {
        try {
            logger.info("Fetching inventory with ID: {}", productId);
            return inventoryRepository.findByProductId(productId);
        } catch (InventoryNotFoundException e) {
            logger.error("InventoryNotFoundException: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error fetching inventory: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch inventory: " + e.getMessage());
        }
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "getInventoryByProductIdFallback")
    public Inventory getInventoryByProductId(@NotNull @Min(1) Integer productId) {
        try {
            logger.info("Fetching inventory for Product ID: {}", productId);
            Inventory inventory = inventoryRepository.findByProductId(productId);
            if (inventory == null) {
                logger.warn("Inventory not found for Product ID: {}", productId);
                throw new InventoryNotFoundException("Inventory not found for Product ID: " + productId);
            }
            return inventory;
        } catch (InventoryNotFoundException e) {
            logger.error("InventoryNotFoundException: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error fetching inventory by Product ID: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch inventory by Product ID: " + e.getMessage());
        }
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "getAllInventoriesFallback")
    public List<Inventory> getAllInventories() {
        try {
            logger.info("Fetching all inventories");
            return inventoryRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching all inventories: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch all inventories: " + e.getMessage());
        }
    }
    
    
    public Product getProductByInventoryId(int id) {
    	RestTemplate restTemplate = builder.build();
        String baseUrl = "http://localhost:8082/"+"api/products/"+id;
        ResponseEntity<Product> product = restTemplate.getForEntity(baseUrl, Product.class);
        return product.getBody();
    }
    

    // Fallback Methods

    public Inventory addInventoryFallback(Inventory inventory, Throwable t) {
        logger.error("Fallback for addInventory. Reason: {}", t.getMessage());
        return null; // Alternatively, return a default Inventory object or a meaningful response
    }

    public void deleteInventoryFallback(Integer inventoryId, Throwable t) {
        logger.error("Fallback for deleteInventory. Reason: {}", t.getMessage());
        // You can implement alternative logic or notify the system
    }

    public Inventory updateInventoryFallback(Integer inventoryId, Inventory inventory, Throwable t) {
        logger.error("Fallback for updateInventory. Reason: {}", t.getMessage());
        return null; // Alternatively, return a default Inventory object or a meaningful response
    }

    public Inventory getInventoryFallback(Integer inventoryId, Throwable t) {
        logger.error("Fallback for getInventory. Reason: {}", t.getMessage());
        return null; // Alternatively, return a default Inventory object or a meaningful response
    }

    public Inventory getInventoryByProductIdFallback(Integer productId, Throwable t) {
        logger.error("Fallback for getInventoryByProductId. Reason: {}", t.getMessage());
        return null; // Alternatively, return a default Inventory object or a meaningful response
    }

    public List<Inventory> getAllInventoriesFallback(Throwable t) {
        logger.error("Fallback for getAllInventories. Reason: {}", t.getMessage());
        return Collections.emptyList(); // Return an empty list or a default value
    }
}