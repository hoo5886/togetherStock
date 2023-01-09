package com.stock.togetherStock.repository;

import com.stock.togetherStock.model.Comment;
import com.stock.togetherStock.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
