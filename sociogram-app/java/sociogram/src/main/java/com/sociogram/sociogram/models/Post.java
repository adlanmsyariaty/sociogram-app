package com.sociogram.sociogram.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
  private Integer id;

  private String userId;

  private String imageUrl;

  private String caption;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "postId", referencedColumnName = "id")
  private List<Comment> comments = new ArrayList<>();

  private Timestamp createdAt;

  public Post() {}

  public Post(Integer id, String userId, String imageUrl, String caption, List<Comment> comments, Timestamp createdAt) {
    super();
    this.userId = userId;
    this.imageUrl = imageUrl;
    this.comments = comments;
    this.caption = caption;
    this.createdAt = createdAt;
  }

  public Integer getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public String getCaption() {
    return caption;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }
}
