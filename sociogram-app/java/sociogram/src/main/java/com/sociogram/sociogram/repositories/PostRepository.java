package com.sociogram.sociogram.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sociogram.sociogram.models.Post;

public interface PostRepository extends MongoRepository<Post, String> {

  List<Post> findAll();
}
