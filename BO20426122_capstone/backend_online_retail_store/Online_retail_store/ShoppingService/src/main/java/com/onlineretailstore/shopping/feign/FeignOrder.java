package com.onlineretailstore.shopping.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.onlineretailstore.shopping.entity.Order;

@FeignClient(name = "order", url = "http://localhost:8085/")
public interface FeignOrder {

	@PostMapping("api/order")
	public ResponseEntity<Order> addOrder(@RequestBody Order order);

}
