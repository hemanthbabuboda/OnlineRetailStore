package com.onlineretailstore.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineretailstore.shopping.entity.CustomerOrder;
import com.onlineretailstore.shopping.entity.Order;

@Repository
public interface CustomerOrderDao extends JpaRepository<CustomerOrder, Integer> {

	List<Order> findByOrder(Integer customerId);
}
