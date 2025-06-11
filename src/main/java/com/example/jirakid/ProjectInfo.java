package com.example.jirakid;

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
