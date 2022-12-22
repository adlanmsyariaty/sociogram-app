package com.sociogram.sociogram.models;


import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Comments")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String userId;

  private long postId;

  private String message;

  @CreationTimestamp
  private Timestamp createdAt;

  public Comment() {}

  public Comment(long id, String userId, long postId, String message, Timestamp createdAt) {
    super();
    this.userId = userId;
    this.postId = postId;
    this.message = message;
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

  public long getPostId() {
    return postId;
  }
  public void setPostId(long postId) {
    this.postId = postId;
  }

  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
