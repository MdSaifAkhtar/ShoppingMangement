package com.example.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.Entity.Product;
import com.example.shopping.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InventoryService {

	
	 @Autowired
	    private ProductRepository productRepository;

	    public Product checkInventory() {
	       
	        return productRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("Product not found"));
	    }

	    public void updateInventory(Long productId, int quantity) {
	        Product product = productRepository.findById(productId)
	                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
	        if (product.getQuantity() < quantity) {
	            throw new IllegalArgumentException("Not enough inventory");
	        }
	        product.setQuantity(product.getQuantity() - quantity);
	        productRepository.save(product);
	    }
	}
	