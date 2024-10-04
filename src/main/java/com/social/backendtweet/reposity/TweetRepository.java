package com.social.backendtweet.reposity;

import com.social.backendtweet.model.Tweet;
import com.social.backendtweet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findAllByIsTweetTrueOrderByCreatedAtDesc();

    List<Tweet> findByRetweetUserContainsOrUser_IdAndIsTweetTrueOrderByCreatedAtDesc(User user, Long userId);

    List<Tweet> findByLikesContainingOrderByCreatedAtDesc(User user);

    @Query("Select t From Tweet  t JOIN t.likes I where l.user.id=:userld ")
    List<Tweet> findByLikeUserID(Long userId);



}
