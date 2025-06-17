package com.example.jirakid.service;

import com.example.jirakid.exception.ResourceNotFoundException;
import com.example.jirakid.model.Task;
import com.example.jirakid.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task updateTask(Long id, Task request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }
        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }

        task.setUpdatedAt(Instant.now());
        return taskRepository.save(task);
    }
}
