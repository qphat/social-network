package com.social.backendtweet.dto;

import lombok.Data;

@Data
public class LikeDTO {
    private Long id;
    private UserDTO user;
    private TweetDTO tweet;
}
