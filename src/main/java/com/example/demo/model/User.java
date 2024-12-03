package com.example.demo.model;

import java.security.Permission;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tbUser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String edv;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String number;

    @Column
    private boolean admin;

    @Column
    private String ets;

    @Column
    private String image;

    @Column
    private String description;

    @OneToMany(mappedBy = "user")
    private Set<Ability> abilitys;
    
    @OneToMany(mappedBy = "user")
    private Set<Carrer> carrers;

}
