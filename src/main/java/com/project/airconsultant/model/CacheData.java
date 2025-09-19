package com.project.airconsultant.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity(name = "cache_data")
public class CacheData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "cache_name")
    private String cacheName;
    @Column(name = "cache_key")
    private String cacheKey;
    @Column(name = "cache_value")
    private byte[] cacheValue;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
