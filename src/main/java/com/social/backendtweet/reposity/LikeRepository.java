package com.social.backendtweet.reposity;

import com.social.backendtweet.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("SELECT l FROM Like l WHERE l.tweet.id = :tweetId AND l.user.id = :userId")
    Optional<Like> findByTweetIdAndUserId(@Param("tweetId") Long tweetId, @Param("userId") Long userId);

    @Query("SELECT COUNT(l) FROM Like l WHERE l.tweet.id = :tweetId")
    long countByTweetId(@Param("tweetId") Long tweetId);

    @Query("SELECT COUNT(l) > 0 FROM Like l WHERE l.tweet.id = :tweetId AND l.user.id = :userId")
    boolean existsByTweetIdAndUserId(@Param("tweetId") Long tweetId, @Param("userId") Long userId);
}
