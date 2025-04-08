package com.onlineretailstore.shopping.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.onlineretailstore.shopping.entity.Cart;

@FeignClient(name = "cart", url = "http://localhost:8084/")
public interface FeignCart {
	@PostMapping("api/cart")
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart);

	@GetMapping("api/cart/{cartId}")
	public ResponseEntity<Cart> getCart(@PathVariable Integer cartId);

	@PutMapping("api/cart/{cartId}")
	public ResponseEntity<Cart> updateCart(@PathVariable Integer cartId, @RequestBody Cart cart);

	@DeleteMapping("api/cart/{cartId}")
	public ResponseEntity<String> deleteCart(@PathVariable Integer cartId);

}
