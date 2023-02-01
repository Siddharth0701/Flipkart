package com.flipkart.flipkartapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flipkart.flipkartapi.model.Seller;

@Repository
public interface SellorRepository extends JpaRepository<Seller, Long> {

}
