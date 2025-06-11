package com.example.jirakid;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
class ProjectController {
    private final ProjectRepository projectRepository;
    ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Project> getProjectById(@PathVariable long id) {
        var project = projectRepository.findById(id)
                .orElseThrow(()-> new ProjectNotFoundException("Project not found"));

        return ResponseEntity.ok(project);
    }

    record CreateProjectPayload(
            @NotEmpty(message="Title is required")
            String title,
            @NotEmpty(message="Owner is required")
            String owner
    ) {}

    @PostMapping
    ResponseEntity<ProjectInfo> createProject(@RequestBody @Valid CreateProjectPayload payload) {
        var project = new Project();
        project.setTitle(payload.title);
        project.setOwner(payload.owner);
        project.setCreatedAt(Instant.now());
        var savedProject = projectRepository.save(project);
        var url = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(savedProject.getId());
        return ResponseEntity.created(url).build();
    }

    record UpdateProjectPayload(
            @NotEmpty(message="Title is required")
            String title,
            @NotEmpty(message="Owner is required")
            String owner
    ) {}

    @PutMapping("/{id}")
    ResponseEntity<Void> updateProject(@PathVariable long id, @RequestBody @Valid UpdateProjectPayload payload) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        project.setTitle(payload.title());
        project.setOwner(payload.owner());
        project.setUpdatedAt(Instant.now());

        projectRepository.save(project);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    void deleteProject(@PathVariable long id) {
        var project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        projectRepository.delete((Project) project);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    ResponseEntity<Void> handleProjectNotFound(ProjectNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
