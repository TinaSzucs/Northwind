package com.sparta.northwind.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.northwind.entities.Employee;
import com.sparta.northwind.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.client.SimpleBufferingClientHttpRequest.addHeaders;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository repo;

    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/getEmplyee")
    public List<Employee> listEmployee(){
        return repo.findAll();
    }

    @GetMapping("/getLastName")
    public List<String> getLastName(){
        List<String> result = repo.findAll()
                .stream()
                .map(employee -> employee.getLastName())
                .toList();

        return result;
    }

    @GetMapping("/emplloyee/{id}")
    public ResponseEntity<String> getOrderByID(@PathVariable String id){
        ResponseEntity<String> response;

        HttpHeaders header = new HttpHeaders();
        header.add("content-type", "application/json");

        try {
            int integerId = Integer.parseInt(id);
            Optional<Employee> result = repo.findById(integerId);

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

    @PutMapping("/add-employee/{id}/{address}")
    public ResponseEntity<String> addEmplyoee(@PathVariable String id,
                                              @PathVariable String address) {
        HttpHeaders headers = addHeaders();
        String message = "";
        ResponseEntity<String> res;

        try {
//
            Employee employee = new Employee();
            employee.setId(id);
            employee.setAddress(address);
            repo.save(employee);
            message = addEmplyoee() + id + " was saved\"}";
            res = new ResponseEntity<>(message, headers, HttpStatus.CREATED);
        } catch (Exception e) {
            res = handleError(e, headers);
        }
        return res;
    }

    public ResponseEntity handleError(Exception e,
                                      HttpHeaders headers) {
        String message = "{\"message\":\"something went wrong\"}";
        ResponseEntity<String> res = new ResponseEntity<>(message, headers,
                HttpStatus.BAD_GATEWAY);
        e.printStackTrace();
        return res;
    }
}
