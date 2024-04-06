package com.example.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.Entity.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {

}
