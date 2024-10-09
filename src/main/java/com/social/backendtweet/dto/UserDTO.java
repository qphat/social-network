package com.social.backendtweet.dto;

import com.social.backendtweet.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDTO {
    private Long userId;
    private String userName;
    private String email;
    private String image;
    private String location;
    private String wbSite;
    private String birthDate;
    private String mobile;
    private String backgroundImage;
    private boolean req_user;
    private boolean login_with_google;
    private List<UserDTO> followers = new ArrayList<>();
    private List<UserDTO> following = new ArrayList<>();
    private boolean isFollowed;
    private boolean isVerified;

    // Phương thức chuyển đổi từ User sang UserDTO
    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setImage(user.getImage());
        userDTO.setLocation(user.getLocation());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setMobile(user.getMobile());
        userDTO.setBackgroundImage(user.getBackgroundImage());
        userDTO.setReq_user(user.isReq_user());
        userDTO.setLogin_with_google(user.isLogin_with_google());
        userDTO.setFollowers(user.getFollowers().stream().map(UserDTO::toUserDTO).collect(Collectors.toList()));
        userDTO.setFollowing(user.getFollowing().stream().map(UserDTO::toUserDTO).collect(Collectors.toList()));
        userDTO.setFollowed(user.isFollowed());
        userDTO.setVerified(user.isVerified());
        return userDTO;
    }
}
