package com.social.backendtweet.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TweetReplyReques {
    private String content;
    private Long TweetId;
    private LocalDateTime createdAt;
    private String image;
    private String video;

}
