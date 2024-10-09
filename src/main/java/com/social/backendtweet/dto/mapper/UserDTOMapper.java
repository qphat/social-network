package com.social.backendtweet.dto.mapper;

import com.social.backendtweet.dto.UserDTO;
import com.social.backendtweet.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTOMapper {
    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getId());
        userDTO.setUserName(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setImage(user.getImage());
        userDTO.setLocation(user.getLocation());
        userDTO.setWbSite(user.getWebsite());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setMobile(user.getMobile());
        userDTO.setBackgroundImage(user.getBackgroundImage());
        userDTO.setReq_user(user.isReq_user());
        userDTO.setLogin_with_google(user.isLogin_with_google());
        userDTO.setFollowers(user.getFollowers().stream().map(UserDTOMapper::toUserDTO).collect(Collectors.toList()));
        userDTO.setFollowing(user.getFollowing().stream().map(UserDTOMapper::toUserDTO).collect(Collectors.toList()));
        userDTO.setVerified(user.isVerified());

        return userDTO;
    }

    public static List<UserDTO> toUserDTOs(List<User> users) {
        return users.stream().map(UserDTOMapper::toUserDTO).collect(Collectors.toList());
    }
}
