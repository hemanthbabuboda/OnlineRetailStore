package com.onlineretailstore.shopping.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.onlineretailstore.shopping.entity.Inventory;

@FeignClient(name = "inventory", url = "http://localhost:8083/api/inventory/")
public interface FeignInventory {

	@PostMapping
	public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory);

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInventory(@PathVariable Integer id);

	@PutMapping("/{id}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable Integer id, @RequestBody Inventory inventory);

	@GetMapping("/{id}")
	public ResponseEntity<Inventory> getInventory(@PathVariable Integer id);

	@GetMapping("/product/{productId}")
	public ResponseEntity<Inventory> getInventoryByProductId(@PathVariable Integer productId);

}
