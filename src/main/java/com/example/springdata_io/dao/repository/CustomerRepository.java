package com.example.springdata_io.dao.repository;

import com.example.springdata_io.dao.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
