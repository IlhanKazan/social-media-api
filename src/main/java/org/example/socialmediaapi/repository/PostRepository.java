package org.example.socialmediaapi.repository;

import org.example.socialmediaapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM SOCIAL_MEDIA_API.POSTS", nativeQuery = true)
    List<Post> getAll();

}
