package com.sociogram.sociogram.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sociogram.sociogram.helpers.ResponseHandler;
import com.sociogram.sociogram.models.Post;
import com.sociogram.sociogram.repositories.PostRepository;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  public ResponseEntity<Object> getAllPost() {
    try {
      List<Post> posts = postRepository.findAll();

      return ResponseHandler.generateResponse("Success to get all post data", HttpStatus.OK, posts);
    } catch (Exception e) {
      System.out.println(e);
      return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  public ResponseEntity<Object> addPost(Post postData) {
    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String userId = auth.getPrincipal().toString();

      Post newPost = new Post();
      newPost.setCaption(postData.getCaption());
      newPost.setImageUrl(postData.getImageUrl());
      newPost.setUserId(userId);
      postRepository.save(newPost);

      return ResponseHandler.generateResponse("Success to create new post", HttpStatus.CREATED, newPost);
    } catch (Exception e) {
      System.out.println(e);
      return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }
}
