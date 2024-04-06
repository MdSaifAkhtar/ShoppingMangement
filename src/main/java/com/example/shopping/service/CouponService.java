package com.example.shopping.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.Entity.Coupon;
import com.example.shopping.repository.CouponRepository;

@Service
public class CouponService {
	
	
	 @Autowired
	    private CouponRepository couponRepository;

	    
	    private Map<Long, String> usedCoupons = new HashMap<>();

	    public double validateAndApplyCoupon(Long userId, String couponCode) {
	        if (couponCode == null || couponCode.isEmpty()) {
	            return 0; 
	        }

	       
	        if (couponCode.equals(usedCoupons.get(userId))) {
	            throw new IllegalArgumentException("Coupon already used by this user");
	        }

	        Coupon coupon = couponRepository.findByCode(couponCode)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid coupon code"));

	       
	        usedCoupons.put(userId, couponCode);

	      
	        return coupon.getDiscount();
	    }
	

}
