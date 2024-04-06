package com.example.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.shopping.Entity.PaymentResult;

public interface PaymentResultRepository  extends JpaRepository<PaymentResult, Long>{

}
