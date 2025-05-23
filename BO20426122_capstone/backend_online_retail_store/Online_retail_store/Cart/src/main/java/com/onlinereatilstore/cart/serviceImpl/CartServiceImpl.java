package com.onlinereatilstore.cart.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinereatilstore.cart.entity.Cart;
import com.onlinereatilstore.cart.exception.CartNotFoundException;
import com.onlineretailstore.cart.repository.CartDao;
import com.onlineretailstore.cart.service.CartService;

/**
 * Service implementation for managing shopping carts.
 * This class provides methods to add, search, update, and delete carts.
 */
@Service
public class CartServiceImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    CartDao repo;

    /**
     * Adds a new cart to the repository.
     *
     * @param cust The cart to be added.
     * @return The saved cart.
     */
    @Override
    public Cart addCart(Cart cust) {
        logger.info("Adding cart with ID: {}", cust.getCartId());
        Cart savedCart = repo.save(cust);
        logger.info("Cart added with ID: {}", savedCart.getCartId());
        return savedCart;
    }

    /**
     * Searches for a cart by its ID.
     *
     * @param cartId The ID of the cart to search for.
     * @return The found cart.
     * @throws CartNotFoundException if the cart is not found.
     */
    @Override
    public Cart searchCart(Integer cartId) throws CartNotFoundException {
        logger.info("Searching for cart with ID: {}", cartId);
        Optional<Cart> cust = repo.findById(cartId);
        if (cust.isPresent()) {
            logger.info("Cart found with ID: {}", cartId);
            return cust.get();
        } else {
            logger.warn("Cart with ID {} not found.", cartId);
            throw new CartNotFoundException("Cart with ID " + cartId + " not found.");
        }
    }

    /**
     * Updates an existing cart.
     *
     * @param cartId The ID of the cart to update.
     * @param cart   The cart object containing updated information.
     * @return The updated cart.
     * @throws CartNotFoundException if the cart is not found.
     */
    @Override
    public Cart updateCart(Integer cartId, Cart cart) throws CartNotFoundException {
        logger.info("Updating cart with ID: {}", cartId);
        if (repo.findById(cartId).isPresent()) {
            Cart updatedCart = new Cart(cartId, cart.getLineItem());
            Cart savedCart = repo.save(updatedCart);
            logger.info("Cart updated with ID: {}", savedCart.getCartId());
            return savedCart;
        } else {
            logger.warn("Cart with ID {} not found for update.", cartId);
            throw new CartNotFoundException("Cart with ID " + cartId + " not found.");
        }
    }

    /**
     * Deletes a cart by its ID.
     *
     * @param cartId The ID of the cart to delete.
     * @return A message indicating the result of the operation.
     * @throws CartNotFoundException if the cart is not found.
     */
    @Override
    public String deleteCart(Integer cartId) throws CartNotFoundException {
        logger.info("Deleting cart with ID: {}", cartId);
        Optional<Cart> cust = repo.findById(cartId);
        if (cust.isPresent()) {
            repo.delete(cust.get());
            logger.info("Cart with ID {} successfully deleted.", cartId);
            return "Successfully Deleted";
        } else {
            logger.warn("Cart with ID {} not found for deletion.", cartId);
            throw new CartNotFoundException("Cart with ID " + cartId + " not found.");
        }
    }
}
