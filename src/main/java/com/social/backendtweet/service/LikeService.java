package com.social.backendtweet.service;

import com.social.backendtweet.exception.TweetException;
import com.social.backendtweet.exception.UserException;
import com.social.backendtweet.model.Like;
import com.social.backendtweet.model.User;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    Optional<Like> findByTweetIdAndUserId(Long userId, Long tweetId) throws UserException, TweetException;
    List<Like> getAllLikes(Long tweetId) throws TweetException;
    Like likeTweet(Long tweetId, User user) throws TweetException;
}