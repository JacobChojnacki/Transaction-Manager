package com.example.springdata_io.service;

import com.example.springdata_io.dao.entity.Product;
import com.example.springdata_io.dao.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductByID(Long index) {
        return productRepository.findById(index);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

}
