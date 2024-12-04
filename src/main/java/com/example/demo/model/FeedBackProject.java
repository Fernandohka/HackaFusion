package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FeedBackProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean priv;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    
    @ManyToOne
    @JoinColumn(name="projectId")
    private Project project;
}
