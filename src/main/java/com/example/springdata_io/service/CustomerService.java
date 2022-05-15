package com.example.springdata_io.service;

import com.example.springdata_io.dao.entity.Customer;
import com.example.springdata_io.dao.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Iterable<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findCustomerByID(Long index) {
        return customerRepository.findById(index);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
