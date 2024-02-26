package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.User;

public interface UserRepo extends JpaRepository<User,Long>{
	
	Optional<User> findByEmailAndPassword(String Username, String Password);

}
