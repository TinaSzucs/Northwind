package com.sparta.northwind.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.northwind.entities.Product;
import com.sparta.northwind.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductControl {
    @Autowired
    ProductRepository repository;
    @Autowired
    ObjectMapper mapper;

    @GetMapping("/product/getAllProducts")
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/product/getById/{id}")
    public Optional<Product> getProductById(@PathVariable Integer id) {
        return repository.findById(id);
    }

    @PutMapping("/product/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");

        return new ResponseEntity<>(mapper.writeValueAsString(repository.save(product)), headers, HttpStatus.OK);
    }

    @PatchMapping("/product/update")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");

        return new ResponseEntity<>(mapper.writeValueAsString(repository.save(product)), headers, HttpStatus.OK);
    }

    @DeleteMapping("/product/deleteById/{id}")
    public ResponseEntity<String> addProduct(@PathVariable Integer id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }



}
