package com.example.demo.controllers;

import com.example.demo.model.Category;
import com.example.demo.model.Orders;
import com.example.demo.model.Dish;
import com.example.demo.services.OrderService;
import com.example.demo.services.DishService;
import com.example.demo.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    /**
     * Retrieves the list of all dishes and adds them to the model.
     *
     * @param model the model to which the list of dishes will be added
     * @return the name of the view template to display the dishes
     */
    @GetMapping("/admin/dishes")
    public String getDishesList(Model model) {
        List<Dish> dishes = dishService.getAllDishes();
        model.addAttribute("dishes", dishes);
        return "admin/dishes";
    }

    /**
     * Provides the necessary attributes for adding a new dish.
     *
     * @param model the model to which a new Dish object and list of categories will be added
     * @return the name of the view template to display the add dish form
     */
    @GetMapping("/admin/dishes/add")
    public String addDish(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("dish", new Dish());
        return "admin/dish-add";
    }

    /**
     * Saves a new dish to the database.
     *
     * @param dish the Dish object to be saved
     * @return a redirect to the dishes list view
     */
    @PostMapping("/admin/dishes/add")
    public String saveDish(@ModelAttribute("dish") Dish dish) {
        Category category = categoryService.getCategoryById(dish.getCategory().getId());
        dish.setCategory(category);
        dishService.saveDish(dish);
        return "redirect:/admin/dishes";
    }

    /**
     * Provides the necessary attributes for editing an existing dish.
     *
     * @param id the ID of the dish to be edited
     * @param model the model to which the dish and list of categories will be added
     * @return the name of the view template to display the edit dish form
     */
    @GetMapping("/admin/dishes/{id}")
    public String editDish(@PathVariable("id") Long id, Model model) {
        Dish dish = dishService.findDishById(id);
        model.addAttribute("dish", dish);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/dish-edit";
    }

    /**
     * Updates an existing dish in the database.
     *
     * @param dish the Dish object to be updated
     * @return a redirect to the dishes list view
     */
    @PostMapping("/admin/dishes/{id}")
    public String updateDish(@ModelAttribute("dish") Dish dish) {
        Category category = categoryService.getCategoryById(dish.getCategory().getId());
        dish.setCategory(category);
        dishService.saveDish(dish);
        return "redirect:/admin/dishes";
    }

    /**
     * Retrieves the list of all orders and adds them to the model.
     *
     * @param model the model to which the list of orders will be added
     * @return the name of the view template to display the orders
     */
    @GetMapping("/admin/orders")
    public String showOrderList(Model model) {
        List<Orders> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/orders";
    }
}
