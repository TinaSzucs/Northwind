package com.sparta.northwind.controller;

import com.sparta.northwind.entities.Customer;
import com.sparta.northwind.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/add-customer/{id}/{companyName}")
    public ResponseEntity<String> addCustomer(@PathVariable String id,
                                              @PathVariable String companyName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        String message = "";
        ResponseEntity<String> res;

        try {
            Customer customer = new Customer();
            customer.setId(id);
            customer.setCompanyName(companyName);
            repo.save(customer);
            message = "user " + customer.getId() + " saved";
            res = new ResponseEntity<>(message, headers, HttpStatus.CREATED);
        } catch (Exception e) {
            message = "something went wrong, try again";
            res = new ResponseEntity<>(message, headers, HttpStatus.BAD_GATEWAY);
            e.printStackTrace();
        }
        return res;
    }

    @PatchMapping("/update/{id}/{companyName}")
    public ResponseEntity<String> update(@PathVariable String id,
                                         @PathVariable String companyName) {
        ResponseEntity<String> res;
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        String message = "";
        try {
            Optional<Customer> customer = repo.findById(id);

            if(customer.isPresent()) {
                Customer c = customer.get();
                c.setCompanyName(companyName);
                message = "{\"message\":\"customer " + id + " was updated\"}";
                res = new ResponseEntity<>(message, headers, HttpStatus.OK);
                repo.save(c);
            } else {
                message = "{\"message\":\"customer " + id + " was not found\"}";
                res = new ResponseEntity<>(message, headers, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            message = "{\"message\":\"something went wrong\"}";
            res = new ResponseEntity<>(message, headers, HttpStatus.BAD_GATEWAY);
            e.printStackTrace();
        }
        return res;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> removeCustomer(@PathVariable String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        ResponseEntity<String> res;
        String message = "";
        try {
            repo.deleteById(id);
            message = "{\"message\":\"customer " + id + " deleted\"}";
            res = new ResponseEntity<>(message, headers, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            message = "{\"message\":\"something went wrong\"}";
            res = new ResponseEntity<>(message, headers, HttpStatus.BAD_GATEWAY);
            e.printStackTrace();
        }
        return res;
    }
}
