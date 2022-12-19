package com.sociogram.sociogram.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sociogram.sociogram.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
