package com.example.demo.beans;

import com.example.demo.model.Category;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * The Menu class represents a collection of categories in the application.
 * It is a Spring-managed bean that can be used to manage and manipulate category data.
 */
@Component
public class Menu implements Serializable {

    private List<Category> categories;

    /**
     * Constructs a new Menu instance with an empty list of categories.
     */
    public Menu() {
        this.categories = new ArrayList<>();
    }

    /**
     * Returns the list of categories.
     *
     * @return a list of Category objects
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * Sets the list of categories.
     *
     * @param categories a list of Category objects to set
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * Adds a new category to the list of categories.
     *
     * @param category the Category object to add
     */
    public void addCategory(Category category) {
        categories.add(category);
    }
}
