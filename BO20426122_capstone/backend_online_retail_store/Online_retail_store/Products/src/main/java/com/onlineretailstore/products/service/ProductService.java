package com.onlineretailstore.products.service;

import java.util.List;

import com.onlineretailstore.products.entity.Product;

public interface ProductService {

	public Product addProduct(Product product);
	public void deleteProduct(Integer productId);
	public Product updateProduct(Integer productId, Product product);
	public Product getProduct(Integer productId);
	public List<Product> getAllProducts();
}
