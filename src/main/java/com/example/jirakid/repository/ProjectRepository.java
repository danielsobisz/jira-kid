package com.example.jirakid.repository;

import com.example.jirakid.dto.ProjectInfo;
import com.example.jirakid.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findById(long id);
}