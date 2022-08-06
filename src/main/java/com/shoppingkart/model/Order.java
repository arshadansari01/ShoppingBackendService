package com.shoppingkart.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name="orders")
@TypeDef(
        name="jsonb", typeClass = JsonBinaryType.class
)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    private Integer cartId;

    private Integer userId;

    @Column(columnDefinition = "jsonb")
    @Type(type="jsonb")
    private List<CartItem> orderItems;

    private BigDecimal orderTotal;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

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

    public List<CartItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<CartItem> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }
}
