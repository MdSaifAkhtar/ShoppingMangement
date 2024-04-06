package com.example.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
