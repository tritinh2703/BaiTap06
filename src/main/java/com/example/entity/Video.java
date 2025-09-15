package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String url;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    // getters & setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}
