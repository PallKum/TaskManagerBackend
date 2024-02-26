package com.example.service;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.LoginDto;
import com.example.Entity.User;
import com.example.repository.UserRepo;
import com.example.service.exception.UserExitsException;



@Service
public class UserService {
	
	@Autowired
	UserRepo repo;
	
	public void saveUser(User user) {
//		Optional<User> opuser= repo.findById(user.getId());
//		if(opuser.isPresent()) {
//			throw new UserExitsException("User Already exits");
//		}
		repo.save(user);
		
	}
	
	public List<User> getUsers (){
		return repo.findAll();
	}
	
	public Optional<User> getUserById(long id) {
		return repo.findById(id);
	}

	public void deleteAllUser() {
		repo.deleteAll();
	}
	
	public User loginUser(LoginDto login) {
		System.out.print("login"+login.getEmail()+"pass");
		Optional<User> opuser= repo.findByEmailAndPassword(login.getEmail(),login.getPassword() );
		System.out.print(opuser+"<====opuser");
		if(opuser.isPresent()) {
			return opuser.get();
		}
		else {
			return null;
		}
			
	}
}
