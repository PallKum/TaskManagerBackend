package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {

}
