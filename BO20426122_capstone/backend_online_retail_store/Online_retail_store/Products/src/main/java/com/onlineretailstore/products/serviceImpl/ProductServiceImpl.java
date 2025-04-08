package com.onlineretailstore.products.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineretailstore.products.entity.Product;
import com.onlineretailstore.products.exception.InvalidProductDataException;
import com.onlineretailstore.products.exception.ProductNotFoundException;
import com.onlineretailstore.products.repository.ProductRepository;
import com.onlineretailstore.products.service.ProductService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private static final String CIRCUIT_BREAKER_NAME = "productService";

    @Autowired
    private ProductRepository productRepo;

    @Override
//    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "addProductFallback")
    public Product addProduct(Product product) {
        try {
            logger.info("Adding product: {}", product.getProductName());

            // Additional validation if needed
            if (product.getProductName() == null || product.getProductName().isBlank()) {
                logger.warn("Invalid product data: {}", product);
                throw new InvalidProductDataException("Product name is required and cannot be blank.");
            }

            if (product.getProductPrice() == null || product.getProductPrice() <= 0) {
                logger.warn("Invalid product price: {}", product);
                throw new InvalidProductDataException("Product price must be greater than 0.");
            }

            Product savedProduct = productRepo.save(product);
            logger.info("Product added with ID: {}", savedProduct.getProductId());
            return savedProduct;
        } catch (InvalidProductDataException e) {
            logger.error("InvalidProductDataException: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while adding product: {}", e.getMessage());
            throw new RuntimeException("Error adding product", e);
        }
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "deleteProductFallback")
    public void deleteProduct(Integer productId) {
        try {
            logger.info("Deleting product with ID: {}", productId);
            if (!productRepo.existsById(productId)) {
                logger.warn("Product with ID: {} not found for deletion", productId);
                throw new ProductNotFoundException("Product not found with ID: " + productId);
            }

            productRepo.deleteById(productId);
            logger.info("Product with ID: {} deleted successfully", productId);
        } catch (ProductNotFoundException e) {
            logger.error("ProductNotFoundException: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while deleting product: {}", e.getMessage());
            throw new RuntimeException("Error deleting product", e);
        }
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "updateProductFallback")
    public Product updateProduct(Integer productId, Product product) {
        try {
            logger.info("Updating product with ID: {}", productId);

            // Additional validation if needed
            if (product.getProductName() == null || product.getProductName().isBlank()) {
                logger.warn("Invalid product data for update: {}", product);
                throw new InvalidProductDataException("Product name is required and cannot be blank.");
            }

            if (product.getProductPrice() == null || product.getProductPrice() <= 0) {
                logger.warn("Invalid product price for update: {}", product);
                throw new InvalidProductDataException("Product price must be greater than 0.");
            }

            Optional<Product> existingProductOpt = productRepo.findById(productId);
            if (existingProductOpt.isPresent()) {
                Product existingProduct = existingProductOpt.get();
                existingProduct.setProductName(product.getProductName());
                existingProduct.setProductDescription(product.getProductDescription());
                existingProduct.setProductPrice(product.getProductPrice());

                Product updatedProduct = productRepo.save(existingProduct);
                logger.info("Product with ID: {} updated successfully", updatedProduct.getProductId());
                return updatedProduct;
            } else {
                logger.warn("Product with ID: {} not found for update", productId);
                throw new ProductNotFoundException("Product not found with ID: " + productId);
            }
        } catch (ProductNotFoundException | InvalidProductDataException e) {
            logger.error("Exception during product update: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while updating product: {}", e.getMessage());
            throw new RuntimeException("Error updating product", e);
        }
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "getProductFallback")
    public Product getProduct(Integer productId) {
        try {
            logger.info("Fetching product with ID: {}", productId);
            return productRepo.findById(productId)
                    .orElseThrow(() -> {
                        logger.warn("Product with ID: {} not found", productId);
                        return new ProductNotFoundException("Product not found with ID: " + productId);
                    });
        } catch (ProductNotFoundException e) {
            logger.error("ProductNotFoundException: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while fetching product: {}", e.getMessage());
            throw new RuntimeException("Error fetching product", e);
        }
    }

    @Override
    @CircuitBreaker(name = CIRCUIT_BREAKER_NAME, fallbackMethod = "getAllProductsFallback")
    public List<Product> getAllProducts() {
        try {
            logger.info("Fetching all products");
            List<Product> products = productRepo.findAll();
            logger.info("Fetched {} products", products.size());
            return products;
        } catch (Exception e) {
            logger.error("Unexpected error while fetching all products: {}", e.getMessage());
            throw new RuntimeException("Error fetching all products", e);
        }
    }

    // Fallback Methods with Logging
    public Product addProductFallback(Product product, Throwable t) {
        logger.error("Fallback for addProduct. Reason: {}", t.getMessage());
        // You can choose to return a default product or throw a custom exception
        throw new RuntimeException("Service is currently unavailable. Please try again later.");
    }

    public void deleteProductFallback(Integer productId, Throwable t) {
        logger.error("Fallback for deleteProduct. Reason: {}", t.getMessage());
        throw new RuntimeException("Service is currently unavailable. Please try again later.");
    }

    public Product updateProductFallback(Integer productId, Product product, Throwable t) {
        logger.error("Fallback for updateProduct. Reason: {}", t.getMessage());
        throw new RuntimeException("Service is currently unavailable. Please try again later.");
    }

    public Product getProductFallback(Integer productId, Throwable t) {
        logger.error("Fallback for getProduct. Reason: {}", t.getMessage());
        throw new RuntimeException("Service is currently unavailable. Please try again later.");
    }

    public List<Product> getAllProductsFallback(Throwable t) {
        logger.error("Fallback for getAllProducts. Reason: {}", t.getMessage());
        return Collections.emptyList(); // Alternatively, throw a RuntimeException
    }
}