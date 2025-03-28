package org.example.socialmediaapi.repository;

import org.example.socialmediaapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByStatus(int status);
    Post findByPostIdAndStatus(Long id, int value);
}
