package com.example.shopping.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "coupons")
public class Coupon {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String code;
	    private double Discount;
	    
		public Coupon(Long id, String code, double discount) {
			super();
			this.id = id;
			this.code = code;
			Discount = discount;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public double getDiscount() {
			return Discount;
		}

		public void setDiscount(double discount) {
			Discount = discount;
		}
	   

}
