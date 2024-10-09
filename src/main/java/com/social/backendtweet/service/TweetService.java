package com.social.backendtweet.service;

import com.social.backendtweet.exception.TweetException;
import com.social.backendtweet.exception.UserException;
import com.social.backendtweet.model.Tweet;
import com.social.backendtweet.model.User;
import com.social.backendtweet.request.TweetReplyReques;

import java.util.List;

public interface TweetService {

    public Tweet createTweet(Tweet req, User user)
        throws UserException;


    public List<Tweet> findAllTweets()
            throws TweetException;

    public Tweet reTweet(Long tweetId, User user)
        throws UserException, TweetException;

    public Tweet findTweetById(Long tweetId)
        throws TweetException;

    public void deleteTweetById(Long tweetId)
        throws TweetException;

    public Tweet removeRetweet(Long tweetId, User user)
        throws TweetException, UserException;

    public Tweet createReplyTweet(TweetReplyReques req, Long tweetId, User user)
        throws TweetException, UserException;

    public List<Tweet> getUserTweets(Long userId)
        throws UserException;

    public List<Tweet> findByLikeContainUser(Long userId)
        throws UserException;



}
