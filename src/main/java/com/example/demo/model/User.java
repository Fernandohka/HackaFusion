package com.example.demo.model;

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

    @OneToMany()
    private Set<Ability> abilitys;
    
    @OneToMany()
    private Set<Carrer> carrers;

    @OneToMany()
    private Set<MessageTopic> messagesTopic;

    @OneToMany()
    private Set<Question> questions;

    @OneToMany()
    private Set<Answer> answers;

    @OneToMany()
    private Set<Vote> votes;

    @OneToMany()
    private Set<Chat> chats;

    @OneToMany()
    private Set<Project> projects;

    @OneToMany()
    private Set<FeedBackProject> feedBackProjects;

    @OneToMany()
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEts() {
        return ets;
    }

    public void setEts(String ets) {
        this.ets = ets;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
