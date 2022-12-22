package com.sociogram.sociogram.services;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sociogram.sociogram.helpers.ResponseHandler;
import com.sociogram.sociogram.models.Post;
import com.sociogram.sociogram.repositories.PostRepository;

@Service
public class PostService {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private AmazonS3 s3Client;

  @Value("${application.bucket.name}")
  private String bucketName;

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

  public ResponseEntity<Object> imageUpload(MultipartFile imageFile) {
    try {
      File convertedFile = new File(imageFile.getOriginalFilename());

      FileOutputStream fos = new FileOutputStream(convertedFile);

      fos.write(imageFile.getBytes());
      fos.close();

      String fileName = "sociogramimage/IMG_" + System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();

      s3Client.putObject(new PutObjectRequest(bucketName, fileName, convertedFile).withCannedAcl(CannedAccessControlList.PublicRead));

      HashMap<String, String> response = new HashMap<String, String>();
      response.put("imageUrl", s3Client.getUrl(bucketName, fileName).toString());

      String filePath = new File("").getAbsolutePath() + "\\" + convertedFile;
      File file = new File(filePath);
      file.delete();

      return ResponseHandler.generateResponse("Success to upload new image", HttpStatus.CREATED, response);
    } catch (Exception e) {
      System.out.println(e);
      return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }
}
