package com.example.demo.services;

import com.example.demo.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repo.DishRepository;

import java.util.List;

/**
 * The DishService class provides methods for managing Dish entities.
 * It interacts with the DishRepository to perform CRUD operations on dishes.
 */
@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    /**
     * Retrieves all dishes from the repository.
     *
     * @return a list of all Dish objects
     */
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    /**
     * Finds a dish by its ID.
     *
     * @param id the ID of the dish to find
     * @return the Dish object if found, or null if not found
     */
    public Dish findDishById(Long id) {
        return dishRepository.findById(id).orElse(null);
    }

    /**
     * Saves a new dish or updates an existing dish in the repository.
     *
     * @param newDish the Dish object to save
     * @return the saved Dish object
     */
    public Dish saveDish(Dish newDish) {
        return dishRepository.save(newDish);
    }

    /**
     * Deletes a dish by its ID.
     *
     * @param id the ID of the dish to delete
     */
    public void deleteDishById(Long id) {
        dishRepository.deleteById(id);
    }
}
