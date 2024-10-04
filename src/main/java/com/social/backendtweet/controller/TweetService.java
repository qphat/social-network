package com.social.backendtweet.controller;

import com.social.backendtweet.exception.TweetExcepction;
import com.social.backendtweet.exception.UserException;
import com.social.backendtweet.model.Tweet;
import com.social.backendtweet.model.User;
import com.social.backendtweet.request.TweetReplyReques;

import java.util.List;

public interface TweetService {

    public Tweet createTweet(Tweet req, User user)
        throws UserException;


    public List<Tweet> findAllTweets()
            throws TweetExcepction;

    public Tweet reTweet(Long tweetId, User user)
        throws UserException, TweetExcepction;

    public Tweet findTweetById(Long tweetId)
        throws TweetExcepction;

    public void deleteTweetById(Long tweetId)
        throws TweetExcepction;

    public Tweet removeRetweet(Long tweetId, User user)
        throws TweetExcepction, UserException;

    public Tweet createReplyTweet(TweetReplyReques req, Long tweetId, User user)
        throws TweetExcepction, UserException;

    public List<Tweet> getUserTweets(Long userId)
        throws UserException;

    public List<Tweet> findByLikeContainUser(Long userId)
        throws UserException;



}
