// src/test/java/com/technischools/tasktracker/service/TaskServiceTest.java
package com.technischools.tasktracker.service;

import com.technischools.tasktracker.model.Task;
import com.technischools.tasktracker.model.TaskStatus;    // ← tu dopisane
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
        // when
        Task task = taskService.createTask("Testowe");

        // then
        assertNotNull(task.getId());
        assertEquals("Testowe", task.getName());
        assertEquals(TaskStatus.TODO, task.getStatus());    // ← TaskStatus.TODO
    }

    @Test
    void getAllTasks_ShouldReturnAllCreatedTasks() {
        // given
        taskService.createTask("A");
        taskService.createTask("B");

        // when
        List<Task> all = taskService.getAllTasks();

        // then
        assertEquals(2, all.size());
    }

    @Test
    void updateTaskStatus_ShouldUpdateStatus() {
        // given
        Task task = taskService.createTask("Zadanie");

        // when
        Task updatedTask = taskService.updateTaskStatus(task.getId(), TaskStatus.IN_PROGRESS);

        // then
        assertEquals(TaskStatus.IN_PROGRESS, updatedTask.getStatus());  // ← TaskStatus.IN_PROGRESS
    }

    @Test
    void updateTaskStatus_ShouldThrowException_WhenTaskNotFound() {
        // when & then
        assertThrows(RuntimeException.class, () ->
                taskService.updateTaskStatus(999, TaskStatus.DONE)              // ← TaskStatus.DONE
        );
    }
}
