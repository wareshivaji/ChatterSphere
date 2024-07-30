package com.chattersphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chattersphere.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
