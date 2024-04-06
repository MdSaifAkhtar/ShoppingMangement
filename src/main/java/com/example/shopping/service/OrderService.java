package com.example.shopping.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.Entity.Order;
import com.example.shopping.Entity.PaymentResult;
import com.example.shopping.Entity.Product;
import com.example.shopping.repository.OrderRepository;
import com.example.shopping.repository.PaymentResultRepository;

@Service
public class OrderService {
	 @Autowired
	  private PaymentResultRepository paymentResultRepository;
	
	 @Autowired
	    private OrderRepository orderRepository;
	    
	    @Autowired
	    private InventoryService inventoryService;
	    
	    @Autowired
	    private CouponService couponService;

	    public Order placeOrder(Long userId, int quantity, String couponCode) {
	        
	        Product product = inventoryService.checkInventory();
	        if (quantity > product.getQuantity()) {
	            throw new IllegalArgumentException("Requested quantity exceeds available inventory");
	        }

	        
	        double discountPercentage = couponService.validateAndApplyCoupon(userId, couponCode);
	        double totalCost = (product.getPrice() * quantity) * (1 - (discountPercentage / 100));

	        // Update inventory
	        inventoryService.updateInventory(product.getId(), quantity);

	        // Create and save order
	        Order order = new Order();
	        order.setUserId(userId);
	        order.setQuantity(quantity);
	        order.setAmount(totalCost);
	        order.setCoupon(couponCode);
	       
	        return orderRepository.save(order);
	    }
	    
	    public PaymentResult makePayment(Long userId, Long orderId, double amount) {
	        // Find the order
	        Order order = orderRepository.findByIdAndUserId(orderId, userId)
	                .orElseThrow(() -> new IllegalArgumentException("Order not found or does not belong to user"));

	        // Check if the order is already paid
	        if (order.isPaid()) {
	            throw new IllegalStateException("Order is already paid for");
	        }

	        // Mock payment processing
	        PaymentResult paymentResult = processPayment(order, amount);

	        // Update order status based on payment result
	        if ("successful".equals(paymentResult.getStatus())) {
	            order.setPaid(true);
	            orderRepository.save(order); // Update the order as paid
	        }

	        // Save and return the payment result
	        return paymentResultRepository.save(paymentResult);
	    }

	    private PaymentResult processPayment(Order order, double amount) {
	        
	        Random rand = new Random();
	        int response = rand.nextInt(5); // Generates a random number between 0 and 4

	        String status;
	        String description;
	        String transactionId = "tran" + System.currentTimeMillis(); // Mock transaction ID

	        switch (response) {
	            case 0:
	                status = "successful";
	                description = "Payment successful";
	                break;
	            case 1:
	                status = "failed";
	                description = "Payment Failed as amount is invalid";
	                break;
	            case 2:
	                status = "failed";
	                description = "Payment Failed from bank";
	                break;
	            case 3:
	                status = "failed";
	                description = "Payment Failed due to invalid order id";
	                break;
	            default:
	                status = "failed";
	                description = "No response from payment server";
	                break;
	        }

	        return new PaymentResult(order.getUserId(), order.getId(), transactionId, status, description);
	    }

}
