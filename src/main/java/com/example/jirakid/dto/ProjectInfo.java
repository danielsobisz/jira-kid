package com.example.jirakid.dto;

import com.example.jirakid.model.Project;

import java.time.Instant;

/**
 * Projection for {@link Project}
 */
public interface ProjectInfo {
    Long getId();
    String getTitle();
    String getOwner();
    Instant getCreatedAt();
}
