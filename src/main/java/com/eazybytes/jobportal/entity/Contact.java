package com.eazybytes.jobportal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name="CONTACT")
@Getter
@Setter
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",nullable = false)
    private Long id;

    @Column(name="NAME",nullable = false)
    private String name;

    @Column(name="MESSAGE",nullable = false)
    private String message;

    @Column(name="EMAIL",nullable = false)
    private String email;

    @Column(name="USER_TYPE",nullable = false)
    private String userType;

    @Column(name="SUBJECT",nullable = false)
    private String subject;

    @ColumnDefault("'NEW'")
    @Column(name="STATUS",nullable = false,length = 20)
    private String status;

    @Column(name="CREATED_AT",nullable = false)
    private Instant createdAt;

    @Column(name="CREATED_BY",nullable = false,length = 20)
    private String createdBy;

    @Column(name="UPDATED_AT",nullable = false)
    private Instant updatedAt;

    @Column(name="UPDATED_BY",nullable = false,length = 20)
    private String updatedBy;



}
