package org.example.socialmediaapi.repository;

import org.example.socialmediaapi.entity.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    @Query(value = "SELECT * FROM SOCIAL_MEDIA_API.INTERACTIONS WHERE STATUS = 1", nativeQuery = true)
    List<Interaction> getAll();
}
