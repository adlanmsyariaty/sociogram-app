package com.sociogram.sociogram.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sociogram.sociogram.helpers.ResponseHandler;
import com.sociogram.sociogram.models.Comment;
import com.sociogram.sociogram.models.Post;
import com.sociogram.sociogram.repositories.CommentRepository;
import com.sociogram.sociogram.repositories.PostRepository;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private CommentRepository commentRepository;

  public ResponseEntity<Object> getAllPost() {
    try {
      System.out.println("masuk");
      // List<Post> posts = postRepository.findAll();
      System.out.println("posts");

      Instant time = Instant.now();
      System.out.println(time);

      Comment newComment = new Comment();
      newComment.setPostId(2);
      newComment.setMessage("huhuhu mau kesana juga");
      // newComment.setCreatedAt(time);

      commentRepository.save(newComment);

      List<Comment> comments = commentRepository.findAll();
      System.out.println(comments);

      return ResponseHandler.generateResponse("Success to get all post data", HttpStatus.OK, comments);
    } catch (Exception e) {
      System.out.println(e);
      return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }
}
