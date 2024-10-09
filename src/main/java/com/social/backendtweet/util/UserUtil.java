package com.social.backendtweet.util;

import com.social.backendtweet.model.User;

public class UserUtil {
    public static boolean isReqUser(User reqUser, User user2) {
        return reqUser.getId().equals(user2.getId());
    }

    public static boolean isFollowdByeReqUser(User reqUser, User user2) {
        return reqUser.getFollowing().contains(user2);
    }
}
