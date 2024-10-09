package com.social.backendtweet.Util;

import com.social.backendtweet.dto.UserDTO;
import com.social.backendtweet.model.Tweet;

public class TweetUtil {

    // Kiểm tra xem người dùng đã like tweet hay chưa bằng cách sử dụng UserDTO
    public static boolean isLikeByReqUser(UserDTO reqUser, Tweet tweet) {
        return tweet.getLikes().stream().anyMatch(user -> user.getId().equals(reqUser.getUserId()));
    }

    // Kiểm tra xem người dùng đã retweet hay chưa bằng cách sử dụng UserDTO
    public static boolean isReTweetByReqUser(UserDTO reqUser, Tweet tweet) {
        return tweet.getReTweetUser().stream().anyMatch(user -> user.getId().equals(reqUser.getUserId()));
    }
}
