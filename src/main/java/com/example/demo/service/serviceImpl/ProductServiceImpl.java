package com.example.demo.service.serviceImpl;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Long addProduct(ProductDTO product) {
        Product prod = new Product();
        prod.setName(product.getName());
        prod.setPrice(product.getPrice());
        prod.setDescription(product.getDescription());
        prod.setSku(product.getSku());
        productRepository.save(prod);
        return prod.getProductId();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getReferenceById((long) id);
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
