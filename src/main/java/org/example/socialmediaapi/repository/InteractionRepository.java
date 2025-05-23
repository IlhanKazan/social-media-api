package org.example.socialmediaapi.repository;

import org.example.socialmediaapi.entity.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    List<Interaction> findAllByStatus(int status);
    Interaction findByInteractionIdAndStatus(int id, int status);
    List<Interaction> findAllByType(int type);
    List<Interaction> findAllByPost_PostIdAndType(int postId, int type);
    Interaction findByAccountIdAndPostIdAndTypeAndStatus(int accountId, int postId, int type, int status);
    Interaction findByAccountIdAndPostIdAndStatus(int accountId, int postId, int status);
    List<Interaction> findAllByPostIdAndStatus(int accountId, int status);
}
