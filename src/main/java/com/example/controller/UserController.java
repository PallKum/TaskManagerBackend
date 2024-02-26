package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.LoginDto;
import com.example.Entity.Task;
import com.example.Entity.User;
import com.example.repository.UserRepo;
import com.example.service.UserService;
import com.example.service.exception.UserExitsException;

@RestController
@RequestMapping("/taskApp")
public class UserController {
	
	@Autowired 
	UserService service;
	
	@Autowired 
	UserRepo repo;
	
	@PostMapping("/UserAdd")
	public ResponseEntity<?> AddUser (@RequestBody User user){
		
	try {
		service.saveUser(user);
		return ResponseEntity.ok("User created successfully");
	}
		catch (UserExitsException e){
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@GetMapping("/getALL")
	public List<User> showAllUser(){
		return service.getUsers();
		
	}
	
	@DeleteMapping("/delALL")
	public ResponseEntity<?> deleteAllUser(){
		service.deleteAllUser();
		return ResponseEntity.ok().build();
		
	}
	 @GetMapping("/get/{userId}")
	    public List<Task> getUserTasks(@PathVariable Long userId) {
	        Optional<User> userOptional = repo.findById(userId);
	        if (userOptional.isPresent()) {
	            User user = userOptional.get();
	            return user.getTasks();
	        } else {
	            // Handle user not found scenario
	            return null;
	        }
	 }
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDto loginInfo){
		System.out.print("i am logininfo"+ loginInfo.getEmail());
		User user=service.loginUser(loginInfo);
		if(user !=null) {
			return ResponseEntity.ok(user);
		}
		else 
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
	}

}
