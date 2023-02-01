package com.flipkart.flipkartapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flipkart.flipkartapi.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    
}
