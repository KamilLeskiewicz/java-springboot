package com.technischools.tasktracker.controller;

import com.technischools.tasktracker.model.Task;
import com.technischools.tasktracker.model.TaskStatus;
import com.technischools.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestParam("name") String name) {
        if (name == null || name.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task name is required");
        }
        Task created = taskService.createTask(name);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(
            @PathVariable Integer id,
            @RequestParam("status") String statusParam
    ) {
        TaskStatus status;
        try {
            status = TaskStatus.valueOf(statusParam);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid status value: " + statusParam
            );
        }
        Task updated = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}
