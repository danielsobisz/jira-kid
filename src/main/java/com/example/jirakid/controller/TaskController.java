package com.example.jirakid.controller;

import com.example.jirakid.model.Project;
import com.example.jirakid.model.Task;
import com.example.jirakid.repository.ProjectRepository;
import com.example.jirakid.repository.TaskRepository;
import com.example.jirakid.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/tasks")
public class TaskController {
    private final ProjectRepository projectRepository;
    private final TaskService taskService;

    public TaskController(ProjectRepository projectRepository, TaskService taskService) {
        this.projectRepository = projectRepository;
        this.taskService = taskService;
    }

    @PostMapping("/project/{project_id}")
    public ResponseEntity<Task> createTask(@RequestBody Task task, @PathVariable Long project_id) {
        Optional<Project> projectOpt = projectRepository.findById(project_id);

        if (projectOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Project project = projectOpt.get();

        task.setCreatedAt(Instant.now());
        task.setProject(project);
        project.getTasks().add(task);

        projectRepository.save(project);

        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody Task request) {
        Task task = taskService.updateTask(id, request);
        return ResponseEntity.ok(task);
    }
}
