package com.sociogram.sociogram.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sociogram.sociogram.helpers.ResponseHandler;
import com.sociogram.sociogram.models.Comment;
import com.sociogram.sociogram.repositories.CommentRepository;

@Service
public class CommentService {

  @Autowired
  private CommentRepository commentRepository;

  public ResponseEntity<Object> addComment(Comment commentData) {
    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String userId = auth.getPrincipal().toString();

      Comment newComment = new Comment();
      newComment.setMessage(commentData.getMessage());
      newComment.setPostId(commentData.getPostId());
      newComment.setUserId(userId);
      commentRepository.save(newComment);

      return ResponseHandler.generateResponse("Success to create new comment", HttpStatus.CREATED, newComment);
    } catch (Exception e) {
      System.out.println(e);
      return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

}
