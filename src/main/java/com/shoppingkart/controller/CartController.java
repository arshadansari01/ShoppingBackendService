package com.shoppingkart.controller;

import com.shoppingkart.model.Cart;
import com.shoppingkart.model.CartItem;
import com.shoppingkart.repository.CartRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/cart")


public class CartController {
    private CartRepository cartRepository;

    CartController(CartRepository cartRep) {
        cartRepository = cartRep;

    }

    @PostMapping("/addtocart")
    public void addToCart(@RequestBody Cart cartRequest) {
        Optional<Cart> cartFromDB = cartRepository.findByUserId(cartRequest.getUserId());
        if (cartFromDB.isPresent()) {
            //Add to Existing cart
            Cart existingCart = cartFromDB.get();
            List<CartItem> cartItemsToAdd = cartRequest.getCartItems();
            existingCart.getCartItems().addAll(cartItemsToAdd);


            existingCart.calculateTotal();
            cartRepository.save(existingCart);
        } else {
            //Directly save= new cart will be created internally
            cartRequest.calculateTotal();
            cartRepository.save(cartRequest);
        }
    }

    @GetMapping("/getCart")
    public Optional<Cart> getCart(@RequestParam(name = "userId") Integer userId) {
        Optional<Cart> cartFromDB = cartRepository.findByUserId(userId);
        return cartFromDB;
    }

    @GetMapping("/deleteCartLine")
    public Optional<Cart> deleteCartLine(@RequestParam(name = "productId") Integer productId,
                                         @RequestParam(name = "userId") Integer userId) {
        Optional<Cart> cartFromDB = cartRepository.findByUserId(userId);
        if (cartFromDB.isPresent()) {
            Cart cart = cartFromDB.get();
            List<CartItem> filteredLines = cart.getCartItems().stream()
                    .filter(cartItem -> !cartItem.getProductId().equals(productId))
                    .collect(Collectors.toList());
            cart.setCartItems(filteredLines);
            cart.calculateTotal();
            cartRepository.save(cart);
        }
        return cartFromDB;
    }

    @GetMapping("/incrementQuantity")
    public Optional<Cart> incrementQuantity(@RequestParam(name = "productId") Integer productId,
                                            @RequestParam(name = "userId") Integer userId) {
        Optional<Cart> cartFromDB = cartRepository.findByUserId(userId);
        if (cartFromDB.isPresent()) {
            Cart cart = cartFromDB.get();

            for (int i = 0; i < cart.getCartItems().size(); i++) {
                if (cart.getCartItems().get(i).getProductId().equals(productId)) {
                    int newQuantity = cart.getCartItems().get(i).getQuantity() + 1;
                    cart.getCartItems().get(i).setQuantity(newQuantity);
                }
            }
            cart.calculateTotal();
            cartRepository.save(cart);
        }
        return cartFromDB;
    }

    @GetMapping("/decrementQuantity")
    public Optional<Cart> decrementQuantity(@RequestParam(name = "productId") Integer productId,
                                            @RequestParam(name = "userId") Integer userId) {
        Optional<Cart> cartFromDB = cartRepository.findByUserId(userId);
        if (cartFromDB.isPresent()) {
            Cart cart = cartFromDB.get();

            for (int i = 0; i < cart.getCartItems().size(); i++) {
                if (cart.getCartItems().get(i).getProductId().equals(productId)) {
                    int newQuantity = cart.getCartItems().get(i).getQuantity() - 1;
                    if (newQuantity != 0) {
                        cart.getCartItems().get(i).setQuantity(newQuantity);

                    }
                }
            }
            cart.calculateTotal();
            cartRepository.save(cart);
        }
        return cartFromDB;
    }


}
