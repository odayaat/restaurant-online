package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.beans.CartItem;
import com.example.demo.model.Dish;
import com.example.demo.repo.DishRepository;

import jakarta.servlet.http.HttpSession;

/**
 * The CartService class provides methods for managing the shopping cart.
 * It handles adding, removing, and updating items in the cart stored in the HTTP session.
 */
@Service
public class CartService {

    @Value("${cart.session_key}")
    private String CART_SESSION_KEY;

    private final DishRepository dishRepository;

    /**
     * Constructs a CartService instance with the specified DishRepository.
     *
     * @param dishRepository the repository for accessing dish data
     */
    public CartService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    /**
     * Adds a specified quantity of a dish to the cart.
     *
     * @param dishId the ID of the dish to add
     * @param quantity the quantity of the dish to add
     * @param session the HTTP session to store the cart
     */
    public void addToCart(Long dishId, int quantity, HttpSession session) {
        Dish dish = dishRepository
                .findById(dishId)
                .orElseThrow(() -> new IllegalStateException("Dish not found"));

        System.out.println("Dish fetched");

        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
        }

        for (CartItem item : cart) {
            if (item.getDish().getId().equals(dish.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                session.setAttribute(CART_SESSION_KEY, cart);
                return;
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setDish(dish);
        cartItem.setQuantity(quantity);
        cart.add(cartItem);
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    /**
     * Retrieves the cart from the session.
     *
     * @param session the HTTP session to retrieve the cart from
     * @return the list of CartItem objects in the cart
     */
    public List<CartItem> getCart(HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
        }
        return cart;
    }

    /**
     * Removes a dish from the cart.
     *
     * @param dishId the ID of the dish to remove
     * @param session the HTTP session to store the cart
     */
    public void removeFromCart(Long dishId, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_SESSION_KEY);
        if (cart != null) {
            cart.removeIf(item -> item.getDish().getId().equals(dishId));
        }
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    /**
     * Decreases the quantity of a dish in the cart by one.
     * If the quantity becomes zero, the dish is removed from the cart.
     *
     * @param dishId the ID of the dish to decrease the quantity of
     * @param session the HTTP session to store the cart
     */
    public void decreaseDishQteFromCart(Long dishId, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CART_SESSION_KEY);
        boolean deleteIt = false;

        for (CartItem item : cart) {
            if (item.getDish().getId().equals(dishId)) {
                int quantity = item.getQuantity();
                if (quantity == 1) {
                    deleteIt = true;
                } else {
                    item.setQuantity(quantity - 1);
                    session.setAttribute(CART_SESSION_KEY, cart);
                    return;
                }
            }
        }

        if (deleteIt) {
            this.removeFromCart(dishId, session);
        }
    }

    /**
     * Clears the cart by removing it from the session.
     *
     * @param session the HTTP session to store the cart
     */
    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }

    /**
     * Calculates the total number of items in the cart.
     *
     * @param cart the list of CartItem objects in the cart
     * @return the total number of items in the cart
     */
    public int getCartSize(List<CartItem> cart) {
        int total = 0;
        for (CartItem cartItem : cart) {
            total += cartItem.getQuantity();
        }
        return total;
    }
}
