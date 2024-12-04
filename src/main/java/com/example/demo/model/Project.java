package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name; 

    @Column
    private String description;
    
    @Column
    private boolean status;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private boolean priv;
    
}
