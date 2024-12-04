package com.example.demo.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

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

    @ManyToOne
    @JoinColumn(name="categoryId") 
    private Category category;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user; 

    @OneToMany(mappedBy = "project")
    private Set<ArchivesProject> archives;

    @OneToMany(mappedBy = "project")
    private Set<MessageProject> messages;

    @OneToMany(mappedBy = "project")
    private Set<FeedBackProject> feedbacks;
    
}
