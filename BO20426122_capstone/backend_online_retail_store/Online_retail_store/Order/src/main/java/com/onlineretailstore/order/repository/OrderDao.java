package com.onlineretailstore.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineretailstore.order.entity.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {

}
