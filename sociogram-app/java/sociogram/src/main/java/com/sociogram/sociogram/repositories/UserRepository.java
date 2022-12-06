package com.sociogram.sociogram.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sociogram.sociogram.models.User;

public interface UserRepository extends MongoRepository<User, String> {

  @Query("{email:'?0'}")
  User findUserByEmail(String email);

  List<User> findAll();

}
