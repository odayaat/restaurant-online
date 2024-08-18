package com.example.demo.controllers;

import com.example.demo.beans.CartItem;
import com.example.demo.beans.Menu;
import com.example.demo.model.Category;
import com.example.demo.model.Orders;
import com.example.demo.model.User;
import com.example.demo.services.CartService;
import com.example.demo.services.CategoryService;
import com.example.demo.services.OrderService;
import com.example.demo.services.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MenuController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Resource(name = "autowiredFieldApplicationScope")
    private Menu applicationMenu;

    /**
     * Displays the welcome page.
     *
     * @return the name of the welcome view template
     */
    @GetMapping("/")
    public String welcomePage() {
        return "welcome";
    }

    /**
     * Displays the home page with categories and cart details.
     *
     * @param model the model to which the categories and cart details will be added
     * @param session the HTTP session containing cart information
     * @return the name of the home view template
     */
    @GetMapping("/home")
    public String menuHomePage(Model model, HttpSession session) {
        model.addAttribute("categories", categoryService.getAllCategories());

        List<CartItem> cart = cartService.getCart(session);
        model.addAttribute("cart", cart);
        model.addAttribute("cartLength", cartService.getCartSize(cart));

        return "index.html";
    }

    /**
     * Displays the category page with the selected category and cart details.
     *
     * @param id the ID of the category to be displayed
     * @param model the model to which the category and cart details will be added
     * @param session the HTTP session containing cart information
     * @return the name of the category view template or an error page if the category is not found
     */
    @GetMapping("/category/{id}")
    public String categoryHomePage(@PathVariable("id") long id, Model model, HttpSession session) {
        List<CartItem> cart = cartService.getCart(session);
        model.addAttribute("cart", cart);
        model.addAttribute("cartLength", cartService.getCartSize(cart));

        Category category = categoryService.getCategoryById(id);

        if (category == null) {
            return "error";
        }

        model.addAttribute("category", category);
        return "category/index";
    }

    /**
     * Displays the user's orders page.
     *
     * @param model the model to which the orders and cart details will be added
     * @param session the HTTP session containing cart information
     * @return the name of the orders view template
     */
    @GetMapping("/orders")
    public String myOrders(Model model, HttpSession session) {
        List<CartItem> cart = cartService.getCart(session);
        model.addAttribute("cart", cart);
        model.addAttribute("cartLength", cartService.getCartSize(cart));

        model.addAttribute("orders", orderService.getMyOrders());
        return "my-orders";
    }

    /**
     * Displays the details of a specific order.
     *
     * @param id the ID of the order to be displayed
     * @param model the model to which the order details and cart information will be added
     * @param session the HTTP session containing cart information
     * @return the name of the order details view template
     */
    @GetMapping("/orders/{id}")
    public String orderDetails(@PathVariable("id") long id, Model model, HttpSession session) {
        model.addAttribute("orderInfo", orderService.getOrderInfoById(id));

        List<CartItem> cart = cartService.getCart(session);
        model.addAttribute("cart", cart);
        model.addAttribute("cartLength", cartService.getCartSize(cart));

        return "order-details";
    }

    /**
     * Displays the main menu page with categories.
     *
     * @param model the model to which the categories will be added
     * @return the name of the main menu view template
     */
    @GetMapping("/menu")
    public String main(Model model) {
        model.addAttribute("categories", applicationMenu.getCategories());
        return "index";
    }

    /**
     * Displays the login page with an optional error message.
     *
     * @param error an optional error message indicating login failure
     * @param model the model to which the error message will be added if present
     * @return the name of the login view template
     */
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Incorrect username or password!!");
        }
        return "login.html";
    }

    /**
     * Displays the form for adding a new user.
     *
     * @param model the model to which a new User object will be added
     * @return the name of the add user view template
     */
    @GetMapping("/adduser")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user.html";
    }

    /**
     * Adds a new user to the system.
     *
     * @param user the User object to be added
     * @param result the binding result for validation
     * @param model the model to which error messages will be added if present
     * @return a redirect to the login page or the add user form with error messages
     */
    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        try {
            user.setRole("GUEST");
            userService.save(user);
        } catch (Exception e) {
            model.addAttribute("error", "Please enter a phone number with exactly 10 digits.");
            return "add-user.html"; // Return to the form with error message
        }
        return "redirect:/login";
    }

    /**
     * Adds a dish to the cart.
     *
     * @param dishId the ID of the dish to be added
     * @param categoryId the ID of the category the dish belongs to
     * @param session the HTTP session containing cart information
     * @return a redirect to the category page
     */
    @PostMapping("/cart/add")
    public String addCart(@RequestParam("dishId") Long dishId, @RequestParam("categoryId") Long categoryId,
                          HttpSession session) {
        cartService.addToCart(dishId, 1, session);
        return "redirect:/category/" + categoryId;
    }

    /**
     * Decreases the quantity of a dish in the cart.
     *
     * @param dishId the ID of the dish to be decreased
     * @param session the HTTP session containing cart information
     * @return a redirect to the home page
     */
    @PostMapping("/cart/decrease")
    public String decreaseCart(@RequestParam("dishId") Long dishId, HttpSession session) {
        cartService.decreaseDishQteFromCart(dishId, session);
        return "redirect:/home";
    }

    /**
     * Removes a dish from the cart.
     *
     * @param dishId the ID of the dish to be removed
     * @param session the HTTP session containing cart information
     * @return a redirect to the home page
     */
    @PostMapping("/cart/delete")
    public String deleteFromCart(@RequestParam("dishId") Long dishId, HttpSession session) {
        cartService.removeFromCart(dishId, session);
        return "redirect:/home";
    }

    /**
     * Clears all items from the cart.
     *
     * @param session the HTTP session containing cart information
     * @return a redirect to the home page
     */
    @PostMapping("/cart/delete/all")
    public String deleteAllCart(HttpSession session) {
        cartService.clearCart(session);
        return "redirect:/home";
    }

    /**
     * Checks out the cart and creates an order.
     *
     * @param session the HTTP session containing cart information
     * @return a redirect to the order details page or the home page if the order creation fails
     */
    @PostMapping("/cart/checkout")
    public String checkoutCart(HttpSession session) {
        List<CartItem> cart = cartService.getCart(session);
        Orders order = orderService.createOrderFromCart(cart);

        if (order == null) {
            return "redirect:/home";
        }
        cartService.clearCart(session);

        return "redirect:/orders/" + order.getId();
    }
}
