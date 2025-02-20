package com.social.backendtweet.service.Imp;

import com.social.backendtweet.service.TweetService;
import com.social.backendtweet.exception.TweetException;
import com.social.backendtweet.exception.UserException;
import com.social.backendtweet.model.Tweet;
import com.social.backendtweet.model.User;
import com.social.backendtweet.reposity.TweetRepository;
import com.social.backendtweet.reposity.UserRepository;
import com.social.backendtweet.request.TweetReplyReques;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetServiceImp implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Tweet createTweet(Tweet req, User user) throws UserException {
        Tweet tweet = new Tweet();
        tweet.setContent(req.getContent());
        tweet.setCreatedAt(req.getCreatedAt());
        tweet.setUser(user);
        tweet.setImage(req.getImage());
        tweet.setReplyTweet(req.getReplyTweet());

        return tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> findAllTweets() {
        return tweetRepository.findAllByIsReTweetTrueOrderByCreatedAtDesc();
    }

    @Override
    public Tweet reTweet(Long tweetId, User user) throws UserException, TweetException {
        Tweet tweet = findTweetById(tweetId);
        if (tweet.getReTweetUser().contains(user)) {
            tweet.getReTweetUser().remove(user);
        } else {
            tweet.getReTweetUser().add(user);
        }
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet findTweetById(Long tweetId) throws TweetException {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetException("Tweet not found"));
        return tweet;
    }

    @Override
    public void deleteTweetById(Long tweetId) throws TweetException {
        Tweet tweet = findTweetById(tweetId);
        if (!tweet.equals(tweet.getUser().getId())) {
            throw new TweetException(" you can't delete this tweet");
        }
        tweetRepository.delete(tweet);
    }

    @Override
    public Tweet removeRetweet(Long tweetId, User user) throws TweetException, UserException {
        return null;
    }

    @Override
    public Tweet createReplyTweet(TweetReplyReques req, Long tweetId, User user) throws TweetException, UserException {
        Tweet tweet = new Tweet();
        tweet.setContent(req.getContent());
        tweet.setCreatedAt(req.getCreatedAt());
        tweet.setUser(user);
        tweet.setImage(req.getImage());
        tweet.setReply(true);
        tweet.setReTweet(false);
        tweet.setReplyForTweet(findTweetById(tweetId));

        return tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> getUserTweets(Long userId) throws UserException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));
        return tweetRepository.findByReTweetUserContainsOrUser_IdAndIsReTweetTrueOrderByCreatedAtDesc(user, userId);
    }

    @Override
    public List<Tweet> findByLikeContainUser(Long userId) throws UserException {
        return tweetRepository.findByLikeUserID(userId);
    }
}