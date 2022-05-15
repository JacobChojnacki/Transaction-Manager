package com.example.springdata_io.service;

import com.example.springdata_io.dao.entity.Order;
import com.example.springdata_io.dao.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Iterable<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> findOrderByID(Long index) {
        return orderRepository.findById(index);
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }
}
