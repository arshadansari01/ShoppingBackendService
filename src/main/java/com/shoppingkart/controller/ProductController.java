package com.shoppingkart.controller;

import com.shoppingkart.model.Product;
import com.shoppingkart.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {

    private ProductRepository productRepository;

    ProductController(ProductRepository productRep){
            productRepository=productRep;

    }


    @GetMapping("")
    public List<Product> getProducts(@RequestParam(name = "name") String name) {
        if(name.equals("products") || name.equals("")) {

            return productRepository.findAll();
        }else {
            return productRepository.findByName(name);
        }


    }

    @GetMapping("/brand")
    public List<Product> getProductsByBrand(@RequestParam(name = "name") String brand) {

        return  productRepository.findByBrandName(brand);


    }



}
