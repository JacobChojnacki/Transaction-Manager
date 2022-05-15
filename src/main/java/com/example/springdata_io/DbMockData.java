package com.example.springdata_io;

import com.example.springdata_io.dao.entity.Customer;
import com.example.springdata_io.dao.repository.CustomerRepository;
import com.example.springdata_io.dao.entity.Order;
import com.example.springdata_io.dao.repository.OrderRepository;
import com.example.springdata_io.dao.entity.Product;
import com.example.springdata_io.dao.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbMockData {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public DbMockData(ProductRepository productRepository, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fill() {
        Product product = new Product("Korek", 2.55f, true);
        Product product1 = new Product("Rura", 5f, true);
        Customer customer = new Customer("Jak Kowalski", "Wrocław");
        Customer customer1 = new Customer("Jakub Chojnacki", "Jelenia Góra");
        Set<Product> products = new HashSet<>() {
            {
                add(product);
                add(product1);
            }};
        Order order = new Order(customer, products, LocalDateTime.now(), "in progress");
        Order order1 = new Order(customer1, Collections.singleton(product1), LocalDateTime.now(), "in progress");


        productRepository.save(product);
        productRepository.save(product1);
        customerRepository.save(customer);
        customerRepository.save(customer1);
        orderRepository.save(order);
        orderRepository.save(order1);
    }
}
