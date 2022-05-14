package com.example.springdata_io.controller;

import com.example.springdata_io.dao.entity.Customer;
import com.example.springdata_io.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("customer/all")
    public Iterable<Customer> getAllCustomers() {
        return customerService.findAllCustomers();
    }

    @GetMapping("customer")
    public Optional<Customer> findCustomerByID(@RequestParam Long index) {
        return customerService.findCustomerByID(index);
    }

    @PostMapping("admin/customer")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @PutMapping("customer")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @PatchMapping("admin/customer")
    public void patchCustomer(@RequestParam Long index, @RequestBody Customer customer) {
        Customer customer1 = customerService.findCustomerByID(index)
                .orElseThrow(NoSuchElementException::new);
        boolean ifUpdate = false;

        if (StringUtils.hasLength(customer.getName())) {
            customer1.setName(customer.getName());
            ifUpdate = true;
        }
        if (StringUtils.hasLength(customer.getAddress())) {
            customer1.setName(customer.getAddress());
            ifUpdate = true;
        }
        if (ifUpdate) {
            customerService.addCustomer(customer1);
        }
    }
}
