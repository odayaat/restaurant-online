package com.example.demo.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The Dish class represents a dish entity in the application.
 * It is mapped to the "dish" table in the database.
 */
@Entity
@Table(name = "dish") // Specifies the name of the table
@NoArgsConstructor
public class Dish implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private String description;
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    /**
     * Constructs a new Dish instance with the specified name, price, description, and category.
     *
     * @param name the name of the dish
     * @param price the price of the dish
     * @param description the description of the dish
     * @param category the category the dish belongs to
     */
    public Dish(String name, double price, String description, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    /**
     * Constructs a new Dish instance with the specified name, price, description, category, and image.
     *
     * @param name the name of the dish
     * @param price the price of the dish
     * @param description the description of the dish
     * @param category the category the dish belongs to
     * @param image the image of the dish
     */
    public Dish(String name, double price, String description, Category category, String image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
    }

    /**
     * Returns the ID of the dish.
     *
     * @return the dish ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the dish.
     *
     * @param id the dish ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of the dish.
     *
     * @return the dish name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the dish.
     *
     * @param name the dish name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the dish.
     *
     * @return the dish price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the dish.
     *
     * @param price the dish price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the description of the dish.
     *
     * @return the dish description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the dish.
     *
     * @param description the dish description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the category the dish belongs to.
     *
     * @return the Category object
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category the dish belongs to.
     *
     * @param category the Category object
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Returns the image of the dish.
     *
     * @return the dish image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image of the dish.
     *
     * @param image the dish image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Returns the default quantity of the dish, which is always 1.
     *
     * @return the default quantity
     */
    public int getQuantity() {
        return 1;
    }

    /**
     * Returns the ID of the category the dish belongs to.
     *
     * @return the category ID
     */
    public Long getCategoryId() {
        return category.getId();
    }
}
