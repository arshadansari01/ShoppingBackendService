package com.shoppingkart.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    Cart subject ;

    @BeforeEach
    void setup() {
        subject = new Cart();
        subject.setCartItems(new ArrayList<>());
    }

    @Test
    void calculateTotalShouldReturnAmountMultipliedWithEachCartItemPriceAndQuantity() {
        addItemToCart();

        BigDecimal actual = subject.calculateTotal();

        assertEquals(actual, BigDecimal.valueOf(2000));
    }

    @Test
    void calculateTotalShouldReturnZeroWhenCartItemsAreNotPresent() {
        BigDecimal actual = subject.calculateTotal();

        assertEquals(actual, BigDecimal.valueOf(0));
    }

    @Test
    void abc() {

        Cart obj = new Cart();
        System.out.println(obj.);
        BigDecimal actual = subject.calculateTotal();

        assertEquals(actual, BigDecimal.valueOf(0));
    }

    private void addItemToCart() {
        List<CartItem> cartItems = new ArrayList<>();
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(2);
        cartItem.setPrice(BigDecimal.valueOf(1000));
        cartItems.add(cartItem);
        subject.setCartItems(cartItems);
    }

}