package com.shoppingkart.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name="cart")
@TypeDef(
        name="jsonb", typeClass = JsonBinaryType.class
)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;

    private Integer userId;

    @Column(columnDefinition = "jsonb")
    @Type(type="jsonb")
    private List<CartItem> cartItems;

    private BigDecimal cartTotal;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(BigDecimal cartTotal) {
        this.cartTotal = cartTotal;
    }

    public BigDecimal calculateTotal() {
        BigDecimal total=BigDecimal.ZERO;
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem cartItem = cartItems.get(i);
            Integer quantity = cartItem.getQuantity();
            BigDecimal price = cartItem.getPrice();
            BigDecimal cartItemTotal = price.multiply(BigDecimal.valueOf(quantity));
            total=total.add(cartItemTotal);

        }
        setCartTotal(total);
        return total;


    }

}
