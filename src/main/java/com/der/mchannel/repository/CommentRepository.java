package com.der.mchannel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.der.mchannel.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
