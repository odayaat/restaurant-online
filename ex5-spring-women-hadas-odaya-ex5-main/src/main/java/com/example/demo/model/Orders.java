package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Represents an order placed by a user.
 * This class contains information about the order such as the
 * order date, total price, order ID, list of dishes, and the user
 * who placed the order.
 * <p>
 * The fields are annotated with JPA annotations for ORM mapping.
 * Lombok annotations are used to generate boilerplate code like
 * getters, setters, and constructors.
 *
 * @see Dish
 * @see User
 */
@Entity
@Data
@NoArgsConstructor
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate orderDate;
    private double totalPrice;

    private String orderSId;

    @ManyToMany
    @JoinTable(name = "order_dishes", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<Dish> dishList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Returns the date the order was placed.
     *
     * @return the order date
     */
    public LocalDate getOrderAt() {
        return orderDate;
    }

    /**
     * Returns the total price of the order.
     *
     * @return the total price
     */
    public Double getPrice() {
        return totalPrice;
    }

    /**
     * Returns the list of dishes in the order.
     *
     * @return the list of dishes
     */
    public List<Dish> getProductItems() {
        return dishList;
    }
}
