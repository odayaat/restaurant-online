package com.example.demo.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Category class represents a category entity in the application.
 * It is mapped to the "category" table in the database.
 */
@Entity
@Table(name = "category") // Specifies the name of the table
@NoArgsConstructor
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Dish> dishes = new ArrayList<>();

    /**
     * Constructs a new Category instance with the specified name.
     *
     * @param name the name of the category
     */
    public Category(String name) {
        this.name = name;
    }

    /**
     * Returns the ID of the category.
     *
     * @return the category ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the category.
     *
     * @param id the category ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of the category.
     *
     * @return the category name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name the category name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the list of dishes associated with the category.
     *
     * @return a list of Dish objects
     */
    public List<Dish> getDishes() {
        return dishes;
    }

    /**
     * Sets the list of dishes associated with the category.
     *
     * @param dishes a list of Dish objects
     */
    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }
}
