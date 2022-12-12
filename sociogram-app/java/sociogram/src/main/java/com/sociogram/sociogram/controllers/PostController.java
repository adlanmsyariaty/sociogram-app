package com.sociogram.sociogram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sociogram.sociogram.services.PostService;

@RestController
@RequestMapping(produces="application/json")
@CrossOrigin(origins="*")
public class PostController {

  @Autowired
  PostService postService;

  @GetMapping("/posts")
  public ResponseEntity<Object> getAllPost() {
    ResponseEntity<Object> res = postService.getAllPost();
    return res;
  }
}
