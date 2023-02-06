package com.stock.togetherStock.comment.repository;

import com.stock.togetherStock.comment.domain.Comment;
import com.stock.togetherStock.post.domain.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 일단 보류...
     */
    List<Comment> findAllByPostPostId(Long postId);
}
