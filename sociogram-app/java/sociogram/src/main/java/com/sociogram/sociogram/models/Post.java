package com.sociogram.sociogram.models;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("posts")
public class Post {

  private String _id;
  private String userId;
  private String imageUrl;
  private String caption;
  private List<String> comments;
  private double createdAt;

  public Post(String _id, String userId, String caption, List<String> comments, double createdAt) {
    super();
    this._id = _id;
    this.userId = userId;
    this.caption = caption;
    this.comments = comments;
    this.createdAt = createdAt;
  }

  public String getId() {
    return _id;
  }

  public String getUserId() {
    return userId;
  }

  public String getCaption() {
    return caption;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public List<String> getComments() {
    return comments;
  }

  public double getCreatedAt() {
    return createdAt;
  }
}
