package com.shoppingkart.repository;

import com.shoppingkart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer> {


    List<Product> findByBrandName(String brand);

    List<Product> findByName(String name);
}
