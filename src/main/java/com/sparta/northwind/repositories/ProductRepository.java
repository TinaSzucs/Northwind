package com.sparta.northwind.repositories;

import com.sparta.northwind.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
