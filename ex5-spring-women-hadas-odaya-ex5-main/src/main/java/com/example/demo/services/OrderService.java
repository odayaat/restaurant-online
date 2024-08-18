package com.example.demo.services;

import com.example.demo.repo.DishRepository;
import com.example.demo.repo.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.example.demo.beans.CartItem;
import com.example.demo.beans.OrderInfo;
import com.example.demo.model.Orders;
import com.example.demo.model.Dish;
import com.example.demo.repo.OrderRepository;

import java.time.LocalDate;
import java.util.*;

/**
 * Service class for managing orders.
 * This class provides methods to create orders from a cart,
 * retrieve all orders, get orders for the authenticated user,
 * and fetch order details by ID. It uses various repositories
 * to interact with the database.
 *
 * @see Orders
 * @see Dish
 * @see OrderRepository
 * @see DishRepository
 * @see UserRepository
 */
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;
    private final UserRepository userRepository;

    /**
     * Constructs a new OrderService with the specified repositories.
     *
     * @param orderRepository the repository for managing orders
     * @param dishRepository the repository for managing dishes
     * @param userRepository the repository for managing users
     */
    public OrderService(OrderRepository orderRepository, DishRepository dishRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new order from the items in the cart.
     * <p>
     * This method calculates the total price of the order,
     * retrieves the current authenticated user, and saves the
     * order in the database.
     *
     * @param cart the list of cart items
     * @return the created order
     */
    public Orders createOrderFromCart(List<CartItem> cart) {
        double totalDouble = 0.0;

        List<Dish> dishList = new ArrayList<>();
        for (CartItem cartItem : cart) {
            Dish dish = cartItem.getDish();
            dishList.add(dish);
            totalDouble += cartItem.getTotalPrice();
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Orders orders = null;
        if (auth.getPrincipal() instanceof User user) {
            orders = new Orders();

            orders.setOrderDate(LocalDate.now());
            orders.setTotalPrice(totalDouble);
            orders.setDishList(dishList);
            orders.setUser(userRepository.findByUserName(user.getUsername()).orElseThrow());

            orders.setOrderSId(UUID.randomUUID().toString());

            orderRepository.save(orders);
        }

        return orders;
    }

    /**
     * Retrieves all orders from the database.
     *
     * @return a list of all orders
     */
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves all orders placed by the authenticated user.
     *
     * @return a list of orders placed by the authenticated user
     */
    public List<Orders> getMyOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof User user) {
            return orderRepository.findAllByUser(userRepository.findByUserName(user.getUsername()).orElseThrow());
        }
        return null;
    }

    /**
     * Retrieves the order information by the order ID.
     *
     * @param id the ID of the order to retrieve
     * @return the order information
     */
    public OrderInfo getOrderInfoById(Long id) {
        Orders orders = orderRepository.findById(id).orElseThrow();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderSId(orders.getOrderSId());
        orderInfo.setUser(orders.getUser());
        List<String> dishList = new ArrayList<>();
        for (Dish dish : orders.getDishList()) {
            dishList.add(dish.getName());
        }
        orderInfo.setDishList(dishList);
        return orderInfo;
    }
}
