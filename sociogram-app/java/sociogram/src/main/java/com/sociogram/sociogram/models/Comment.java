package com.sociogram.sociogram.models;

import java.security.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Comments")
public class Comment {

  private int id;
  private int postId;
  private String message;

  @CreationTimestamp
  private Timestamp createdAt;

  public Comment() {}

  public Comment(int id, int postId, String message, Timestamp createdAt) {
    this.postId = postId;
    this.message = message;
    this.createdAt = createdAt;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "postId", nullable = false)
  public int getPostId() {
    return postId;
  }
  public void setPostId(int postId) {
    this.postId = postId;
  }

  @Column(name = "message", nullable = false)
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }

  @Column(name = "createdAt")
  public Timestamp getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
