package com.eazybytes.jobportal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="COMPANIES")
@Getter @Setter
public class Company extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",nullable = false,unique = true)
    private Long Id;

    @Column(name="NAME",nullable = false,unique = true)
    private String name;

    @Column(name="LOGO",length = 500)
    private String logo;

    @Column(name="INDUSTRY",nullable = false,length = 100)
    private String industry;

    @Column(name="SIZE",nullable = false,length = 50)
    private String size;

    @Column(name="RATING",nullable = false,precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(name="LOCATIONS",length = 1000)
    private String locations;

    @Column(name="FOUNDED",nullable = false)
    private Integer founded;

    @Lob
    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="EMPLOYEES")
    private BigInteger employees;

    @Column(name="WEBSITE",length = 500)
    private String website;

    // this attribute is not present in the SQL but mentioned here to get list of all jobs for a company
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true) // orphanRemove removes the record from the DB and alos from the list. if false, only from the list, remians in the DB
    private List<Job> jobs = new ArrayList<>();

    // CascadeType.All
    // CascadeType.PERSISTENT // Saves child entity when parent is saved
    // CascadeType.MERGE // Updates child entity when parent is updated
    // CascadeType.REMOVE // Deltes child entity when parent is deleted
    // CascadeType.REFRESH // Refreshes child when parent is refreshed from DB
    // CascadeType.DETACH  // Detaches the child from persistence context when parent is detached
    // Applies all changes

}
