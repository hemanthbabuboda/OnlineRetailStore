package com.onlineretailstore.order.service;

import com.onlineretailstore.order.entity.Order;
import com.onlineretailstore.order.exception.OrderNotFoundException;

import jakarta.validation.Valid;

public interface OrderService {

    /**
     * Adds a new order.
     *
     * @param order the order to add
     * @return the added order
     * @throws ValidationException if the order is invalid
     */
    Order addOrder(@Valid Order order);

    /**
     * Searches for an order by its ID.
     *
     * @param orderId the ID of the order to search
     * @return the found order, or null if not found
     */
    Order searchOrder(Integer orderId) throws OrderNotFoundException;

    /**
     * Updates an existing order.
     *
     * @param orderId the ID of the order to update
     * @param order   the updated order details
     * @return the updated order
     * @throws ValidationException if the updated order is invalid
     */
    Order updateOrder(Integer orderId, @Valid Order order) throws OrderNotFoundException;

    /**
     * Deletes an order by its ID.
     *
     * @param orderId the ID of the order to delete
     * @return a message indicating the result of the deletion
     */
    String deleteOrder(Integer orderId) throws OrderNotFoundException;
}
