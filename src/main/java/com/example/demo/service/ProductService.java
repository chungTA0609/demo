package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {
    Long addProduct(ProductDTO product);
    Product getProductById(int id);
    List<Product> getAllProducts();
}
