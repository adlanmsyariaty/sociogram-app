package com.sociogram.sociogram.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User  {

  private String _id;
  private String name;
  private String email;
  private String username;
  private String password;

  public User(String _id, String name, String email, String username, String password) {
    super();
    this._id = _id;
    this.name = name;
    this.email = email;
    this.username = username;
    this.password = password;
  }

  public String getId() {
    return _id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String newPassword) {
    this.password = newPassword;
  }

}
