package com.eazybytes.jobportal.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

// Dont give @Entity and @Table as wont be a separate table
@Getter @Setter
@MappedSuperclass
public class BaseEntity {

    @Column(name="CREATED_AT",nullable = false)
    private Instant createdAt;

    @Column(name="CREATED_BY",nullable = false,length = 20)
    private String createdBy;

    @Column(name="UPDATED_AT",nullable = true)
    private Instant updatedAt;

    @Column(name="UPDATED_BY",nullable = true,length = 20)
    private String updatedBy;
}
