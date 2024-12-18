package com.example.demo.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
    private String name;

    
    @Column
    private String password;
    
    @Column
    private String number;
    
    @Column
    private boolean admin;
    
    @Column
    private boolean ets;
    
    @Column
    private Long image;

    
    @Column
    private String description;
    
    @ManyToMany
    private Set<Ability> abilitys;
    
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Carrer> carrers;
    
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<MessageTopic> messagesTopic;
    
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Question> questions;
    
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Answer> answers;
    
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Vote> votes;
    
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Chat> chats;
    
    @ManyToMany()
    private Set<Project> projects;
    
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<FeedBackProject> feedBackProjects;
    
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<Notification> notifications;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEdv() {
        return edv;
    }
    
    public void setEdv(String edv) {
        this.edv = edv;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Long getImage() {
        return image;
    }

    public void setImage(Long image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean getEts() {
        return ets;
    }

    public void setEts(boolean ets) {
        this.ets = ets;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Ability> getAbilitys() {
        return abilitys;
    }

    public void setAbilitys(Set<Ability> abilitys) {
        this.abilitys = abilitys;
    }

    public Set<Carrer> getCarrers() {
        return carrers;
    }

    public void setCarrers(Set<Carrer> carrers) {
        this.carrers = carrers;
    }

    public Set<MessageTopic> getMessagesTopic() {
        return messagesTopic;
    }

    public void setMessagesTopic(Set<MessageTopic> messagesTopic) {
        this.messagesTopic = messagesTopic;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }

    public Set<Chat> getChats() {
        return chats;
    }

    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<FeedBackProject> getFeedBackProjects() {
        return feedBackProjects;
    }

    public void setFeedBackProjects(Set<FeedBackProject> feedBackProjects) {
        this.feedBackProjects = feedBackProjects;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

}
