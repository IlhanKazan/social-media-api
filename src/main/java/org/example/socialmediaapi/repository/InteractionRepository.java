package org.example.socialmediaapi.repository;

import org.example.socialmediaapi.entity.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    List<Interaction> findAllByStatus(int status);
    Interaction findByAccountIdAndStatus(Long id, int status);
    List<Interaction> findAllByType(int type);
    List<Interaction> findAllByPostIdAndType(int postId, int type);
}
