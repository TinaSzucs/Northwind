package com.sparta.northwind.controller;

import com.sparta.northwind.entities.Customer;
import com.sparta.northwind.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository repo;

    @GetMapping("/all-customers")
    public List<Customer> getAllCustomers() {
        List<Customer> customers = null;

        try {
            customers = repo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    @GetMapping("/customer/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id) {
        Optional<Customer> customer = null;
        try {
            customer = repo.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    @GetMapping("/customer-by-country/{country}")
    public List<Customer> getByCountry(@PathVariable String country) {
        List<Customer> customer = null;
        try {
            customer = repo.findByCountry(country);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }


}
