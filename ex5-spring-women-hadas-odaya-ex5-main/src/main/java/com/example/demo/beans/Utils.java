package com.example.demo.beans;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * The Utils class provides utility methods for formatting currency and calculating the total price of items in a cart.
 */
public class Utils {

    private static final Locale ENGLISH_LOCALE = Locale.US;

    /**
     * Formats the given amount as a currency string based on the US locale.
     *
     * @param amount the amount to format
     * @return a string representing the formatted currency
     */
    public String formatCurrency(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(ENGLISH_LOCALE);
        return currencyFormatter.format(amount);
    }

    /**
     * Calculates the total price of items in the given cart.
     *
     * @param cart an array of CartItem objects representing the items in the cart
     * @return the total price of all items in the cart
     */
    public Double getCartTotal(CartItem[] cart) {
        Double total = 0.00;

        for (CartItem cartItem : cart) {
            total += cartItem.getTotalPrice();
        }

        return total;
    }
}
