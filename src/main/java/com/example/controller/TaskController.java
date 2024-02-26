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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Task;
import com.example.Entity.User;
import com.example.service.TaskService;
import com.example.service.UserService;
import com.example.service.exception.UserExitsException;

@RestController
@RequestMapping("/task")
public class TaskController {
	@Autowired
	TaskService service;
	@Autowired
	UserService userservice;
	@PostMapping("/Add")
	public ResponseEntity<?> AddTask (@RequestBody Task taskRequest){
		Optional<User> opuser=userservice.getUserById(taskRequest.getUser().getId());
		
		if (opuser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Create a new task and associate it with the user
        Task task = new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskStatus(taskRequest.getTaskStatus());
        task.setUser(opuser.get());

        // Save the task
        Task savedTask = service.addTask(taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
		
		
	}
	@GetMapping("/getAll")
	public List<Task> getAllTask() {
		return service.getAllTasks();
	}
	
	@PutMapping("/updateTask/{taskId}")
	public ResponseEntity<?> updateTask(@PathVariable Long taskId,@RequestBody String status){
        
		Task task = service.getTaskById(taskId).orElse(null);

        // Check if the task exists
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found with ID: " + taskId);
        }

        // Update the task status
        task.setTaskStatus(status);

        // Save the updated task to the database
         service.addTask(task);

        return ResponseEntity.ok("Task status updated successfully");
    }
	
	@DeleteMapping("/deleteTask/{taskId}")
	public ResponseEntity<?> deleteTask(@PathVariable Long taskId){
		service.deleteTaskbyId(taskId);
		return ResponseEntity.ok("Task Deleted successfully");
		
	}
}
