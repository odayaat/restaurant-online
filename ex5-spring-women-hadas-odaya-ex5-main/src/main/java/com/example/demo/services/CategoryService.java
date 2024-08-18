package com.example.demo.services;

import com.example.demo.model.Category;
import com.example.demo.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing categories.
 * This class provides methods to retrieve all categories and
 * fetch a category by its ID. It uses the {@link CategoryRepository}
 * to interact with the database.
 *
 * @see Category
 * @see CategoryRepository
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Retrieves all categories from the database.
     *
     * @return a list of all categories
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Retrieves a category by its ID.
     * <p>
     * If the category with the specified ID does not exist,
     * this method returns {@code null}.
     *
     * @param id the ID of the category to retrieve
     * @return the category with the specified ID, or {@code null} if not found
     */
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
