package com.sparta.northwind.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.northwind.entities.Order;
import com.sparta.northwind.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    private OrderRepository repo;

    @Autowired
    private ObjectMapper mapper;

    // CREATE
//    @PostMapping


    // READ
    @GetMapping("/allOrders")
    public List<Order> getAllOrders(){
        return repo.findAll();
    }

    @GetMapping("/orderCityNames")
    public List<String> getCityNames(){
        List<String> result = repo.findAll()
                .stream()
                .map(city -> city.getShipCity())
                .toList();

        return result;
    }

    @GetMapping("/orderShippedDate")
    public List<Instant> getShippedDate(){
        List<Instant> result = repo.findAll()
                .stream()
                .map(date -> date.getShippedDate())
                .toList();

        return result;
    }

    // localhost:8080/order ?id=12345
    // localhost:8080/order/12345
    //                      /apple
    @GetMapping("/order/{id}")
    public ResponseEntity<String> getOrderByID(@PathVariable String id){
        ResponseEntity<String> response;

        HttpHeaders header = new HttpHeaders();
        header.add("content-type", "application/json");

        try {
            int integerId = Integer.parseInt(id);
            Optional<Order> result = repo.findById(integerId);

            if (result.isPresent()) {
                response = new ResponseEntity<>(mapper.writeValueAsString(result.get()), header, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>("{\"message\": \"Order not found\"}", header, HttpStatus.OK);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            response = new ResponseEntity<>("{\"message\": \"Json processing error\"}", header, HttpStatus.OK);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response = new ResponseEntity<>("{\"message\": \"Invalid order id\"}", header, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            response = new ResponseEntity<>("{\"message\": \"Order id was not provided\"}", header, HttpStatus.OK);
        }

        return response;
    }




    // UPDATE
//    @PatchMapping



    // DELETE
//    @DeleteMapping

    
}
