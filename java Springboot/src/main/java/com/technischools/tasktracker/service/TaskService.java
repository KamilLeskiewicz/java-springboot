package com.technischools.tasktracker.service;

import com.technischools.tasktracker.model.Task;
import com.technischools.tasktracker.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public Task createTask(String name) {
        Task task = new Task(idCounter.getAndIncrement(), name, TaskStatus.TODO);
        tasks.add(task);
        return task;
    }

    public Task updateTaskStatus(Integer id, TaskStatus newStatus) {
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .map(t -> {
                    t.setStatus(newStatus);
                    return t;
                })
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
}
