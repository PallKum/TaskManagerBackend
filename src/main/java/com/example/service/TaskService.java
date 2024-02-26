package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Task;
import com.example.Entity.User;
import com.example.repository.TaskRepo;
import com.example.repository.UserRepo;

@Service
public class TaskService {
@Autowired
TaskRepo repo;
@Autowired
UserRepo userepo;

public Task addTask(Task task) {
	return repo.save(task);
	
}

public List<Task> getAllTasks(){
	return repo.findAll();
}

public Optional<Task> getTaskById(long id) {
	return repo.findById(id);
}
public void deleteTaskbyId(long id) {
	repo.delete(repo.findById(id).get());
}
}
