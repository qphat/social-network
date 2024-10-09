package com.social.backendtweet.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TweetDTO {
    private Long id;
    private String content;
    private String image;
    private String video;
    private UserDTO user;
    private LocalDateTime createAt;
    private int TotalLikes;
    private int totalReplies;
    private int totalReTweets;
    private boolean isLiked;
    private boolean isReTweet;
    private List<Long>reTweetUserId;
    private List<TweetDTO> replyTweet;

}
