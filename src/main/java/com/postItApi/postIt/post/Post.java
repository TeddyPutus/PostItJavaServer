package com.postItApi.postIt.post;

import com.postItApi.postIt.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String content;
    private Boolean isPrivate;

    @ManyToOne
    User user;

    public Post() {}

    public Post(String title, String content, Boolean isPrivate, User user) {
        this.title = title;
        this.content = content;
        this.isPrivate = isPrivate;
        this.user = user;
    }

    

    public Post(Long id, String title, String content, Boolean isPrivate, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isPrivate = isPrivate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

    

    
}
