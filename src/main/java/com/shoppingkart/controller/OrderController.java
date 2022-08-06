package com.shoppingkart.controller;

import com.shoppingkart.model.Cart;
import com.shoppingkart.model.Order;
import com.shoppingkart.repository.CartRepository;
import com.shoppingkart.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/order")


public class OrderController {

    private CartRepository cartRepository;
    private OrderRepository orderRepository;

    OrderController(CartRepository cartRep, OrderRepository orderRep) {
        cartRepository = cartRep;
        orderRepository = orderRep;
    }

    @PostMapping("/createOrder")
    public void createOrder(@RequestParam(name = "userId") Integer userId) {
        Optional<Cart> cartFromDB = cartRepository.findByUserId(userId);
        if (cartFromDB.isPresent()) {
            Cart cart = cartFromDB.get();
            Order order = new Order();
            order.setCartId(cart.getCartId());
            order.setOrderItems(cart.getCartItems());
            order.setOrderTotal(cart.getCartTotal());
            order.setUserId(cart.getUserId());
            orderRepository.save(order);
            cartRepository.delete(cart);

        }
    }


    @GetMapping("/getOrder")
    public List<Order> getOrder(@RequestParam(name = "userId") Integer userId) {
       return orderRepository.findByUserId(userId);
    }


}
