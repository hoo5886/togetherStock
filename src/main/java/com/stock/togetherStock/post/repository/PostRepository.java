package com.stock.togetherStock.post.repository;

import com.stock.togetherStock.post.domain.Post;
import java.util.List;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post>  findByPostId(Long id);

}
