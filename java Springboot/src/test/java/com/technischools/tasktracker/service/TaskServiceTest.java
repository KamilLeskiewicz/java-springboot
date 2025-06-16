package com.technischools.tasktracker.service;

import com.technischools.tasktracker.model.Task;
import com.technischools.tasktracker.model.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void createTask_ShouldCreateWithTodoStatus() {

        Task task = taskService.createTask("Testowe");


        assertNotNull(task.getId());
        assertEquals("Testowe", task.getName());
        assertEquals(TaskStatus.TODO, task.getStatus());
    }

    @Test
    void getAllTasks_ShouldReturnAllCreatedTasks() {

        taskService.createTask("A");
        taskService.createTask("B");


        List<Task> all = taskService.getAllTasks();


        assertEquals(2, all.size());
    }

    @Test
    void updateTaskStatus_ShouldUpdateStatus() {

        Task task = taskService.createTask("Zadanie");


        Task updatedTask = taskService.updateTaskStatus(task.getId(), TaskStatus.IN_PROGRESS);


        assertEquals(TaskStatus.IN_PROGRESS, updatedTask.getStatus());
    }

    @Test
    void updateTaskStatus_ShouldThrowException_WhenTaskNotFound() {
        assertThrows(RuntimeException.class, () ->
                taskService.updateTaskStatus(999, TaskStatus.DONE)
        );
    }
}
