package com.sociogram.sociogram.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Posts")
public class Post {

  @Id
  @GeneratedValue
  private long id;

  private String userId;

  private String imageUrl;

  private String caption;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "postId", referencedColumnName = "id")
  private List<Comment> comments = new ArrayList<>();

  @CreationTimestamp
  private Timestamp createdAt;

  public Post() {}

  public Post(long id, String userId, String imageUrl, String caption, List<Comment> comments, Timestamp createdAt) {
    super();
    this.userId = userId;
    this.imageUrl = imageUrl;
    this.caption = caption;
    this.createdAt = createdAt;
  }

  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getCaption() {
    return caption;
  }
  public void setCaption(String caption) {
    this.caption = caption;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public String getImageUrl() {
    return imageUrl;
  }
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
