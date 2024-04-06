package com.example.shopping.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_results")
public class PaymentResult {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	 
	    private Long userId;

	    private Long orderId;

	   
	    private String transactionId;

	  
	    private String status;

	    
	    private String description;

	
	    private LocalDateTime paymentDate;


		public PaymentResult(Long id, Long userId, Long orderId, String transactionId, String status,
				String description, LocalDateTime paymentDate) {
			super();
			this.id = id;
			this.userId = userId;
			this.orderId = orderId;
			this.transactionId = transactionId;
			this.status = status;
			this.description = description;
			this.paymentDate = paymentDate;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Long getUserId() {
			return userId;
		}


		public void setUserId(Long userId) {
			this.userId = userId;
		}


		public Long getOrderId() {
			return orderId;
		}


		public void setOrderId(Long orderId) {
			this.orderId = orderId;
		}


		public String getTransactionId() {
			return transactionId;
		}


		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public LocalDateTime getPaymentDate() {
			return paymentDate;
		}


		public void setPaymentDate(LocalDateTime paymentDate) {
			this.paymentDate = paymentDate;
		}
	    
	    

}
