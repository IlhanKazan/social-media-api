package org.example.socialmediaapi.repository;

import org.example.socialmediaapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    /*
    @Query("SELECT a FROM Account a")
    List<Account> getAll(); // Bunun yerine findAll zaten var repoda
     */

    @Query(value = "SELECT * FROM SOCIAL_MEDIA_API.ACCOUNTS", nativeQuery = true)
    List<Account> getAll();

}
