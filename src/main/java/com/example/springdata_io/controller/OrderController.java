package com.example.springdata_io.controller;

import com.example.springdata_io.dao.entity.Order;
import com.example.springdata_io.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("order/all")
    public Iterable<Order> getAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("order")
    public Optional<Order> getOrderByID(@RequestParam Long index) {
        return orderService.findOrderByID(index);
    }

    @PostMapping("order")
    public  Order addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @PutMapping("admin/order")
    public Order updateOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @PatchMapping("admin/order")
    public void patchOrder(@RequestParam Long index, @RequestBody Order order) {
        Order order1 = orderService.findOrderByID(index)
                .orElseThrow(NoSuchElementException::new);
        boolean ifUpdate = false;

        if (StringUtils.hasLength(order.getStatus())) {
            order1.setStatus(order.getStatus());
            ifUpdate = true;
        }
        if (StringUtils.hasLength(String.valueOf(order.getPlaceDate()))) {
            order1.setPlaceDate(order.getPlaceDate());
            ifUpdate = true;
        }
        if (StringUtils.hasLength(String.valueOf(order.getCustomer()))) {
            order1.setCustomer(order.getCustomer());
            ifUpdate = true;
        }
        if (StringUtils.hasLength(String.valueOf(order.getProducts()))) {
            order1.setProducts(order.getProducts());
            ifUpdate = true;
        }
        if (ifUpdate) {
            orderService.addOrder(order1);
        }
    }
}
