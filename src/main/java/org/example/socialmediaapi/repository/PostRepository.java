package org.example.socialmediaapi.repository;

import org.example.socialmediaapi.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByStatus(int status, Pageable pageable);
    Page<Post> findAllByStatusAndAccountId(int status, Long id, Pageable pageable);
    Post findByPostIdAndStatus(Long id, int value);
}
