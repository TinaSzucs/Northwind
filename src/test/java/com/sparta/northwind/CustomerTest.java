package com.sparta.northwind;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.northwind.controller.CustomerController;
import com.sparta.northwind.entities.Customer;
import com.sparta.northwind.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CustomerTest {
    private ObjectMapper objMapper;
    public CustomerController controller;

    @BeforeEach
    public void setUp() {
        objMapper = new ObjectMapper();
        controller = new CustomerController();
    }

    @Test
    public void testGetContactNameById() {
        try {
            Customer result = objMapper.readValue(new URL(
                    "http://localhost:8080/customer/ALFKI"), Customer.class);
            Assertions.assertEquals("Maria Anders", result.getContactName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllCustomers() {
        try {
            List<Customer> customers = objMapper.readValue(new URL(
                    "http://localhost:8080/all-customers"
            ), new TypeReference<List<Customer>>(){});
            Assertions.assertEquals(94, customers.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCustomersByCountry() {
        try {
            List<Customer> customers = objMapper.readValue(new URL(
                    "http://localhost:8080/customer-by-country/italy"
            ), new TypeReference<List<Customer>>() {});
            Assertions.assertEquals(3, customers.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddCustomer() {
        try {
            controller.addCustomer("fake", "fakeCompany");
            Customer result = objMapper.readValue(new URL("" +
                    "http://localhost:8080/customer/fake"), Customer.class);
            Assertions.assertEquals("fakeCompany", result.getCompanyName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
