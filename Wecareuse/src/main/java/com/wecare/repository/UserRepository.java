package com.wecare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecare.entity.Usertable;

@Repository
public interface UserRepository extends JpaRepository<Usertable,String> {
	

}
