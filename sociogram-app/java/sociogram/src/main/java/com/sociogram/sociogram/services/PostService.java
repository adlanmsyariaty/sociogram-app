package com.sociogram.sociogram.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sociogram.sociogram.helpers.ResponseHandler;
import com.sociogram.sociogram.models.Post;
import com.sociogram.sociogram.repositories.PostRepository;

@Service
public class PostService {

  @Autowired
  PostRepository postRepository;

  public ResponseEntity<Object> getAllPost() {
    try {
      List<Post> posts = postRepository.findAll();
      return ResponseHandler.generateResponse("Success to get all post data", HttpStatus.OK, posts);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }
}
