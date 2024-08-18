package com.example.demo;

import com.example.demo.beans.Menu;
import com.example.demo.beans.Utils;
import com.example.demo.model.Dish;
import com.example.demo.model.Category;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.DishRepository;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import jakarta.annotation.PostConstruct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration class for managing application beans and initializing data.
 * <p>
 * This class configures beans for the application and initializes
 * the database with categories and dishes if they are not already present.
 * It logs the initialization process using SLF4J.
 *
 * @see Menu
 * @see Utils
 * @see CategoryRepository
 * @see DishRepository
 */
@Configuration
@Data
public class BeanConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(BeanConfiguration.class);

    private final CategoryRepository categoryRepository;
    private final DishRepository dishRepository;

    /**
     * Constructs a new BeanConfiguration with the specified repositories.
     *
     * @param categoryRepository the repository for managing categories
     * @param dishRepository the repository for managing dishes
     */
    public BeanConfiguration(CategoryRepository categoryRepository, DishRepository dishRepository) {
        this.categoryRepository = categoryRepository;
        this.dishRepository = dishRepository;
    }

    /**
     * Defines a bean with application scope for the menu.
     *
     * @return a new instance of Menu
     */
    @Bean
    @ApplicationScope
    public Menu autowiredFieldApplicationScope() {
        return new Menu();
    }

    /**
     * Defines a utility bean for the application.
     *
     * @return a new instance of Utils
     */
    @Bean
    public Utils utils() {
        return new Utils();
    }

    /**
     * Initializes data by populating the database with categories and dishes.
     * <p>
     * If the database already contains dishes, this method does nothing.
     * Otherwise, it clears the existing categories and dishes and
     * adds a predefined set of categories and dishes.
     */
    @PostConstruct
    public void initData() {
        logger.info("Starting data initialization");

        if (dishRepository.count() > 0) {
            logger.info("Data already initialized");
            return;
        }

        // Clear existing categories and dishes
        dishRepository.deleteAll();
        categoryRepository.deleteAll();

        //add all the categories to SQL

        String[] categories = { "Appetizer", "Dessert", "Main Course", "Drink" };
        Map<String, Category> categoryMap = new HashMap<>();

        for (String categoryName : categories) {
            Category category = new Category();
            category.setName(categoryName);
            Category savedCategory = categoryRepository.save(category);
            categoryMap.put(categoryName, savedCategory);
            logger.info("Saved category: {}", savedCategory);
        }
        //add all the dishes to SQL
        List<Dish> dishes = List.of(
                new Dish("Bruschetta", 89.50, "Grilled bread with tomato, basil, and olive oil.",
                        categoryMap.get("Appetizer"),"bruschetta.jpg"),
                new Dish("Stuffed Mushrooms", 108.00, "Mushrooms filled with cheese and herbs.",
                        categoryMap.get("Appetizer"),"stuffedMushrooms.jpg"),
                new Dish("Caesar Salad", 97.50, "Classic Caesar salad with croutons and Parmesan.",
                        categoryMap.get("Appetizer"),"caesarSalad.jpg"),
                new Dish("Garlic Bread", 106.00, "Bread with garlic and butter.", categoryMap.get("Appetizer"),"garlicBread.jpg"),
                new Dish("Spring Rolls", 97.50, "Crispy rolls with vegetables.", categoryMap.get("Appetizer"),"springRolls.jpg"),
                new Dish("Chicken Wings", 111.00, "Spicy chicken wings.", categoryMap.get("Appetizer"),"spicyChicken.jpg"),
                new Dish("Mozzarella Sticks", 89.00, "Fried mozzarella sticks.", categoryMap.get("Appetizer"),"mozzarellaSticks.jpg"),
                new Dish("Deviled Eggs", 69.50, "Eggs with a creamy filling.", categoryMap.get("Appetizer"),"deviledEggs.jpg"),
                new Dish("Nachos", 99.00, "Tortilla chips with cheese and jalapeños.", categoryMap.get("Appetizer"),"nachos.jpg"),
                new Dish("Caprese Salad", 84.50, "Tomato, mozzarella, and basil.", categoryMap.get("Appetizer"),"capreseSalad.jpg"),
                new Dish("Tiramisu", 78.00, "Traditional Italian dessert with coffee and mascarpone.",
                        categoryMap.get("Dessert"), "tiramisu.jpg"),
                new Dish("Chocolate Lava Cake", 80.00, "Warm chocolate cake with a gooey center.",
                        categoryMap.get("Dessert"),"chocolateLavaCake.jpg"),
                new Dish("Cheesecake", 92.50, "Creamy cheesecake with a graham cracker crust.",
                        categoryMap.get("Dessert"),"cheesecake.jpg"),
                new Dish("Apple Pie", 100.50, "Classic apple pie with a flaky crust.", categoryMap.get("Dessert"),"applePie.jpg"),
                new Dish("Ice Cream Sundae", 57.00, "Ice cream with chocolate sauce and nuts.",
                        categoryMap.get("Dessert"),"iceCreamSundae.jpg"),
                new Dish("Panna Cotta", 73.00, "Creamy dessert with a hint of vanilla.", categoryMap.get("Dessert"),"pannaCotta.jpg"),
                new Dish("Brownies", 68.00, "Rich chocolate brownies.", categoryMap.get("Dessert"),"brownies.jpg"),
                new Dish("Lemon Tart", 96.50, "Tart with a lemon filling.", categoryMap.get("Dessert"),"lemonTart.jpg"),
                new Dish("Fruit Salad", 75.50, "Mixed fruit salad.", categoryMap.get("Dessert"),"fruitSalad.jpg"),
                new Dish("Creme Brulee", 98.00, "Custard dessert with a caramelized top.", categoryMap.get("Dessert"),"cremeBrulee.jpg"),
                new Dish("Margherita Pizza", 104.00, "Pizza with tomato, mozzarella, and basil.",
                        categoryMap.get("Main Course"),"margheritaPizza.jpg"),
                new Dish("Spaghetti Carbonara", 139.50, "Pasta with pancetta, egg, and Parmesan.",
                        categoryMap.get("Main Course"),"spaghettiCarbonara.jpg"),
                new Dish("Grilled Salmon", 150.00, "Salmon fillet grilled to perfection.",
                        categoryMap.get("Main Course"),"grilledSalmonFillet.jpg"),
                new Dish("Steak", 180.00, "Grilled steak with a side of vegetables.", categoryMap.get("Main Course"),"grilledSteak.jpg"),
                new Dish("Chicken Alfredo", 140.00, "Pasta with chicken and Alfredo sauce.",
                        categoryMap.get("Main Course"),"chickenAlfredoPasta.jpg"),
                new Dish("Vegetable Stir Fry", 101.50, "Stir-fried vegetables with soy sauce.",
                        categoryMap.get("Main Course"),"vegetableStirfry.jpg"),
                new Dish("Beef Tacos", 130.00, "Tacos with seasoned beef.", categoryMap.get("Main Course"),"beefTacos.jpg"),
                new Dish("Lamb Chops", 106.00, "Grilled lamb chops.", categoryMap.get("Main Course"),"grilledLambChops.jpg"),
                new Dish("Fish and Chips", 120.50, "Fried fish with French fries.", categoryMap.get("Main Course"),"fishAndChips.jpg"),
                new Dish("Veggie Burger", 110.00, "Burger with a vegetable patty.", categoryMap.get("Main Course"),"veggieBurger.jpg"),
                new Dish("Lemonade", 33.50, "Freshly squeezed lemonade.", categoryMap.get("Drink"),"lemonade.jpg"),
                new Dish("Iced Tea", 42.00, "Refreshing iced tea with a hint of lemon.", categoryMap.get("Drink"),"icedTea.jpg"),
                new Dish("Mojito", 47.00, "Classic cocktail with rum, mint, and lime.", categoryMap.get("Drink"),"mojito.jpg"),
                new Dish("Coca-Cola", 29.50, "Chilled Coca-Cola.", categoryMap.get("Drink"),"cocaCola.jpg"),
                new Dish("Orange Juice", 30.00, "Freshly squeezed orange juice.", categoryMap.get("Drink"),"orangeJuice.jpg"),
                new Dish("Coffee", 50.50, "Hot brewed coffee.", categoryMap.get("Drink"),"coffee.jpg"),
                new Dish("Green Tea", 22.50, "Hot green tea.", categoryMap.get("Drink"),"greenTea.jpg"),
                new Dish("Smoothie", 41.00, "Fruit smoothie.", categoryMap.get("Drink"),"smoothie.jpg"),
                new Dish("Milkshake", 48.50, "Creamy milkshake.", categoryMap.get("Drink"),"milkshake.jpg"),
                new Dish("Water", 10.50, "Bottled water.", categoryMap.get("Drink"),"water.jpg"));

        dishRepository.saveAll(dishes);

        for (Dish dish : dishes) {
            logger.info("Saved dish: {}", dish);
        }

        logger.info("Data initialization completed");
    }
}
