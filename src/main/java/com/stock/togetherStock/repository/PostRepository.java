package com.stock.togetherStock.repository;

import com.stock.togetherStock.model.Member;
import com.stock.togetherStock.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
