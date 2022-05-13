package com.sparta.northwind.repositories;

import com.sparta.northwind.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}