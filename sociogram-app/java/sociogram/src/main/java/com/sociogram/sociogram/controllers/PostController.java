package com.sociogram.sociogram.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sociogram.sociogram.models.Post;
import com.sociogram.sociogram.services.PostService;

@RestController
@RequestMapping(path = "/posts", produces="application/json")
@CrossOrigin(origins="*")
public class PostController {

  @Autowired
  PostService postService;

  @GetMapping("")
  public ResponseEntity<Object> getAllPost() {
    ResponseEntity<Object> res = postService.getAllPost();
    return res;
  }

  @PostMapping("/add")
  public ResponseEntity<Object> createPost(@RequestBody Post newPost) {
    ResponseEntity<Object> res = postService.addPost(newPost);
    return res;
  }

  @PostMapping(path = "/imageUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Object> imageUpload(@RequestParam("image") MultipartFile imageFile) {
    ResponseEntity<Object> res = postService.imageUpload(imageFile);
    return res;
  }
}
