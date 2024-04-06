package com.example.shopping.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.Entity.Coupon;


public interface CouponRepository  extends JpaRepository<Coupon, Long>{

	Optional<Coupon> findByCode(String couponCode);





}
