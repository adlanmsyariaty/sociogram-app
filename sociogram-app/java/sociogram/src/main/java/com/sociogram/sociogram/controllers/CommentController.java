package com.sociogram.sociogram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sociogram.sociogram.models.Comment;
import com.sociogram.sociogram.services.CommentService;

@RestController
@RequestMapping(path = "/comments", produces="application/json")
@CrossOrigin(origins="*")
public class CommentController {

  @Autowired
  CommentService commentService;

  @PostMapping("/add")
  public ResponseEntity<Object> createPost(@RequestBody Comment newComment) {
    ResponseEntity<Object> res = commentService.addComment(newComment);
    return res;
  }
}
