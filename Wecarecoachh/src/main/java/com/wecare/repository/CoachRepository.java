package com.wecare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecare.entity.Coachtable;

@Repository
public interface CoachRepository extends JpaRepository<Coachtable,String> {
	
	
}
