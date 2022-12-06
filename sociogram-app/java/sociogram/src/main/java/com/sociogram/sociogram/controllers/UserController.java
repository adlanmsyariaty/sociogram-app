package com.sociogram.sociogram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sociogram.sociogram.models.User;
import com.sociogram.sociogram.services.UserService;

@RestController
@RequestMapping(path="/users", produces="application/json")
@CrossOrigin(origins="*")
public class UserController {

  @Autowired
  UserService userService;

  class AccessToken {
    public String accessToken;
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody User user) {
    ResponseEntity<Object> res = userService.login(user);
    return res;
  }

  @PostMapping("/signup")
  public ResponseEntity<Object> signup(@RequestBody User newUser) {
    ResponseEntity<Object> res = userService.signup(newUser);
    return res;
  }
}