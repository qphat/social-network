package com.social.backendtweet.dto.mapper;

import com.social.backendtweet.Util.TweetUtil;
import com.social.backendtweet.dto.TweetDTO;
import com.social.backendtweet.dto.UserDTO;
import com.social.backendtweet.model.Tweet;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TweetDTOMapper {

    public static TweetDTO toTweetDTO(Tweet tweet, UserDTO reqUser) {
        // Convert user object to UserDTO
        UserDTO userDTO = UserDTOMapper.toUserDTO(tweet.getUser());

        // Check if the tweet is liked by the requested user (reqUser)
        boolean isLiked = TweetUtil.isLikeByReqUser(reqUser, tweet);

        // Check if the tweet is retweeted by the requested user (reqUser)
        boolean isReTweeted = TweetUtil.isReTweetByReqUser(reqUser, tweet);

        // Get retweet user IDs as a list of Long
        List<Long> reTweetUserId = tweet.getReTweetUser() != null
                ? tweet.getReTweetUser().stream().map(user -> user.getId()).collect(Collectors.toList())
                : Collections.emptyList();

        // Initialize TweetDTO and set properties
        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setId(tweet.getId());
        tweetDTO.setContent(tweet.getContent());
        tweetDTO.setImage(tweet.getImage());
        tweetDTO.setVideo(tweet.getVideo());
        tweetDTO.setUser(userDTO);
        tweetDTO.setCreateAt(tweet.getCreatedAt());
        tweetDTO.setTotalLikes(tweet.getLikes() != null ? tweet.getLikes().size() : 0);
        tweetDTO.setTotalReplies(tweet.getReplyTweet() != null ? tweet.getReplyTweet().size() : 0);
        tweetDTO.setTotalReTweets(tweet.getReTweetUser() != null ? tweet.getReTweetUser().size() : 0);
        tweetDTO.setLiked(isLiked);
        tweetDTO.setReTweet(isReTweeted);
        tweetDTO.setReTweetUserId(reTweetUserId);

        // Map reply tweets to their DTOs
        tweetDTO.setReplyTweet(toTweetDTOs(tweet.getReplyTweet() != null ? tweet.getReplyTweet() : Collections.emptyList(), reqUser));

        return tweetDTO;
    }

    public static List<TweetDTO> toTweetDTOs(List<Tweet> tweets, UserDTO reqUser) {
        // Convert a list of Tweet objects to a list of TweetDTOs
        return tweets != null ? tweets.stream().map(tweet -> toTweetDTO(tweet, reqUser)).collect(Collectors.toList()) : Collections.emptyList();
    }
}