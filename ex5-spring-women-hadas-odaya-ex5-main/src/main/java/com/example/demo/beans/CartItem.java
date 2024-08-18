package com.example.demo.beans;

import java.io.Serializable;

import com.example.demo.model.Dish;

import lombok.Data;

/**
 * Represents an item in the shopping cart.
 * This class stores the dish and its quantity,
 * and provides methods to get the total price,
 * dish details, and a string representation of the item.
 * <p>
 * The methods in this class return information about
 * the dish and the quantity in the cart, and calculate
 * the total price based on the quantity.
 *
 * @see Dish
 */
@Data
public class CartItem implements Serializable {
    private Dish dish;
    private int quantity;

    /**
     * Returns the quantity of the dish in the cart.
     *
     * @return the quantity of the dish
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Returns the total price for the dish based on its quantity.
     *
     * @return the total price for the dish
     */
    public double getTotalPrice() {
        return dish.getPrice() * quantity;
    }

    /**
     * Returns the name of the dish.
     *
     * @return the name of the dish
     */
    public String getDishName() {
        return dish.getName();
    }

    /**
     * Returns the price of the dish.
     *
     * @return the price of the dish
     */
    public Double getDishPrice() {
        return dish.getPrice();
    }

    /**
     * Returns the ID of the dish.
     *
     * @return the ID of the dish
     */
    public Long getDishId() {
        return dish.getId();
    }

    /**
     * Returns a string representation of the cart item.
     *
     * @return a string representation of the cart item
     */
    @Override
    public String toString() {
        return "CartItem{" + "dish=" + dish + ", quantity=" + quantity + '}';
    }
}
