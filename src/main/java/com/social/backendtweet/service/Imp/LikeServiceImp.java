package com.social.backendtweet.service.Imp;

import com.social.backendtweet.service.LikeService;
import com.social.backendtweet.service.TweetService;
import com.social.backendtweet.exception.TweetException;
import com.social.backendtweet.exception.UserException;
import com.social.backendtweet.model.Like;
import com.social.backendtweet.model.Tweet;
import com.social.backendtweet.model.User;
import com.social.backendtweet.reposity.LikeRepository;
import com.social.backendtweet.reposity.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImp implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private TweetRepository tweetRepository;

    public Like like(Long tweetId, User user) throws UserException, TweetException {
        Optional<Like> isLike = likeRepository.findByTweetIdAndUserId(user.getId(), tweetId);

        // Nếu đã like rồi thì unlike
        if (isLike.isPresent()) {
            likeRepository.delete(isLike.get());
            return null;  // Trả về null hoặc một thông báo tùy chỉnh
        }

        // Nếu chưa like thì thực hiện like
        Tweet tweet = tweetService.findTweetById(tweetId);
        Like like = new Like();
        like.setTweet(tweet);
        like.setUser(user);

        return likeRepository.save(like);
    }

    @Override
    public Optional<Like> findByTweetIdAndUserId(Long userId, Long tweetId) throws UserException, TweetException {
        return likeRepository.findByTweetIdAndUserId(userId, tweetId);
    }

    @Override
    public List<Like> getAllLikes(Long tweetId) throws TweetException {
        Tweet tweet = tweetService.findTweetById(tweetId);
        return likeRepository.findByTweet(tweet);
    }

    @Override
    public Like likeTweet(Long tweetId, User user) throws TweetException {
        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new TweetException("Tweet not found"));

        Like like = new Like();
        like.setTweet(tweet);
        like.setUser(user);

        return likeRepository.save(like);
    }
}
