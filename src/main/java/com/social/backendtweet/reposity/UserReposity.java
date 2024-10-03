package com.social.backendtweet.reposity;

import com.social.backendtweet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReposity extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = ?1 OR u.email = ?2")
    public User findByUsernameOrEmail(String username, String email);

    @Query("SELECT DISTINCT u FROM User u WHERE u.username LIKE %:query% OR u.email LIKE %:query%")
    public List<User> searchUser(@Param("query") String query);

}
