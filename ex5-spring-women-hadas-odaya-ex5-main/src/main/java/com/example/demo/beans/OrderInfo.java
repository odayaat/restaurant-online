package com.example.demo.beans;

import java.util.List;

import com.example.demo.model.User;

/**
 * Represents the information of an order.
 * This class contains the order ID, user information,
 * and a list of dishes included in the order.
 * <p>
 * The methods in this class allow access and modification
 * of the order's details, such as the order ID, user, and
 * the list of dishes.
 *
 * @see User
 */
public class OrderInfo {
    private String orderSId;
    private User user;
    private List<String> dishList;

    /**
     * Returns the order ID.
     *
     * @return the order ID
     */
    public String getOrderSId() {
        return orderSId;
    }

    /**
     * Sets the order ID.
     *
     * @param orderSId the new order ID
     */
    public void setOrderSId(String orderSId) {
        this.orderSId = orderSId;
    }

    /**
     * Returns the user who placed the order.
     *
     * @return the user who placed the order
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who placed the order.
     *
     * @param user the new user who placed the order
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the list of dishes in the order.
     *
     * @return the list of dishes
     */
    public List<String> getDishList() {
        return dishList;
    }

    /**
     * Sets the list of dishes in the order.
     *
     * @param dishList the new list of dishes
     */
    public void setDishList(List<String> dishList) {
        this.dishList = dishList;
    }
}
