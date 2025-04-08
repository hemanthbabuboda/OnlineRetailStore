package com.onlineretailstore.order.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineretailstore.order.entity.Order;
import com.onlineretailstore.order.exception.OrderNotFoundException;
import com.onlineretailstore.order.repository.OrderDao;
import com.onlineretailstore.order.service.OrderService;

/**
 * Implementation of the OrderService interface.
 * This class provides methods to manage orders including
 * adding, searching, updating, and deleting orders in the system.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao repo;

    /**
     * Adds a new order to the repository.
     *
     * @param od the order to be added
     * @return the saved order
     */
    @Override
    public Order addOrder(Order od) {
        logger.info("Request to add a new order: {}", od);
        Order savedOrder = repo.save(od);
        logger.info("Order added successfully: {}", savedOrder);
        return savedOrder;
    }

    /**
     * Searches for an order by its ID.
     *
     * @param orderId the ID of the order to search for
     * @return the found order
     * @throws OrderNotFoundException if no order is found with the given ID
     */
    @Override
    public Order searchOrder(Integer orderId) throws OrderNotFoundException {
        logger.info("Request to search for order with ID: {}", orderId);
        Optional<Order> order = repo.findById(orderId);
        if (order.isPresent()) {
            logger.info("Order found: {}", order.get());
            return order.get();
        } else {
            logger.error("Order with ID {} not found.", orderId);
            throw new OrderNotFoundException("Order with ID " + orderId + " not found.");
        }
    }

    /**
     * Updates an existing order.
     *
     * @param orderId the ID of the order to update
     * @param order   the new order data
     * @return the updated order
     * @throws OrderNotFoundException if no order is found with the given ID
     */
    @Override
    public Order updateOrder(Integer orderId, Order order) throws OrderNotFoundException {
        logger.info("Request to update order with ID: {}. New data: {}", orderId, order);
        if (repo.findById(orderId).isPresent()) {
            Order updatedOrder = new Order(orderId, order.getLineItems());
            Order savedOrder = repo.save(updatedOrder);
            logger.info("Order updated successfully: {}", savedOrder);
            return savedOrder;
        } else {
            logger.error("Order with ID {} not found for update.", orderId);
            throw new OrderNotFoundException("Order with ID " + orderId + " not found.");
        }
    }

    /**
     * Deletes an order by its ID.
     *
     * @param orderId the ID of the order to delete
     * @return a message indicating the result of the deletion
     * @throws OrderNotFoundException if no order is found with the given ID
     */
    @Override
    public String deleteOrder(Integer orderId) throws OrderNotFoundException {
        logger.info("Request to delete order with ID: {}", orderId);
        Optional<Order> order = repo.findById(orderId);
        if (order.isPresent()) {
            repo.delete(order.get());
            logger.info("Order with ID {} deleted successfully.", orderId);
            return "Successfully Deleted";
        } else {
            logger.error("Order with ID {} not found for deletion.", orderId);
            throw new OrderNotFoundException("Order with ID " + orderId + " not found.");
        }
    }
}
