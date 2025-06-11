package com.example.jirakid;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<ProjectInfo> findAllByOrderByCreatedAtDesc();
    Optional<Project> findById(long id);
}