package com.example.springdata_io;

import com.example.springdata_io.Product.Product;
import com.example.springdata_io.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Start {

    private ProductRepository productRepository;

    @Autowired
    public Start(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runExample(){
        Product product1 = new Product("Mis", 19.99f, true);
        productRepository.save(product1);
        Product product2 = new Product("Lalka", 29.99f, true);
        productRepository.save(product2);
        Product product3 = new Product("Samochodzik", 19.99f, false);
        productRepository.save(product3);
        Product product4 = new Product("Hantel", 59.99f, true);
        productRepository.save(product4);
        Product product5 = new Product("Szafa", 250.00f, false);
        productRepository.save(product5);
    }
}
