package com.example.shopping.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.Entity.Order;
import com.example.shopping.Entity.PaymentResult;
import com.example.shopping.Entity.Product;
import com.example.shopping.service.CouponService;
import com.example.shopping.service.InventoryService;
import com.example.shopping.service.OrderService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api")
public class ShoppingController {
	
	 @Autowired
	    private InventoryService inventoryService;

	    @Autowired
	    private OrderService orderService;

	    @Autowired
	    private CouponService couponService;

	   
	    @GetMapping("/inventory")
	    public ResponseEntity<?> getInventory() {
	        Product inventory = inventoryService.checkInventory();
	        return ResponseEntity.ok(inventory);
	    }

	   
	    @GetMapping("/fetchCoupons")
	    public ResponseEntity<?> fetchCoupons() {
	        Map<String, Integer> coupons = couponService.fetchAvailableCoupons();
	        return ResponseEntity.ok(coupons);
	    }

	  
	    @PostMapping("/{userId}/order")
	    public ResponseEntity<?> placeOrder(@PathVariable Long userId,
	                                        @RequestParam int qty,
	                                        @RequestParam(required = false) String coupon) {
	        try {
	            Order order = orderService.placeOrder(userId, qty, coupon);
	            return ResponseEntity.ok(order);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.status(404).body(Map.of("description", e.getMessage()));
	        }
	    }

	    // Make payment for an order
	    @PostMapping("/{userId}/{orderId}/pay")
	    public ResponseEntity<?> makePayment(@PathVariable Long userId,
	                                         @PathVariable Long orderId,
	                                         @RequestParam double amount) {
	        try {
	            PaymentResult paymentResult = orderService.makePayment(userId, orderId, amount);
	            return ResponseEntity.ok(paymentResult);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().body(Map.of("description", e.getMessage()));
	        } catch (Exception e) {
	            return ResponseEntity.status(504).body(Map.of("description", "No response from payment server"));
	        }
	    }

	    // View user orders
	    @GetMapping("/{userId}/orders")
	    public ResponseEntity<?> getUserOrders(@PathVariable Long userId) {
	        List<Order> orders = orderService.findOrdersByUserId(userId);
	        return ResponseEntity.ok(orders);
	    }

	    // View specific order details
	    @GetMapping("/{userId}/orders/{orderId}")
	    public ResponseEntity<?> getOrderDetails(@PathVariable Long userId, @PathVariable Long orderId) {
	        try {
	            Order order = orderService.findOrderByIdAndUserId(orderId, userId);
	            return ResponseEntity.ok(order);
	        } catch (EntityNotFoundException e) {
	            return ResponseEntity.status(404).body(Map.of("orderId", orderId, "description", "Order not found"));
	        }
	    }

}
