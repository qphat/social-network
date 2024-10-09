package com.social.backendtweet.controller;

import com.social.backendtweet.util.UserUtil;
import com.social.backendtweet.dto.UserDTO;
import com.social.backendtweet.dto.mapper.UserDTOMapper;
import com.social.backendtweet.exception.TweetException;
import com.social.backendtweet.exception.UserException;
import com.social.backendtweet.model.User;
import com.social.backendtweet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(@RequestHeader("Authorization") String jwtToken)
    throws UserException {

        User user = userService.findUserbyJWT(jwtToken);
        UserDTO userDTO = UserDTO.toUserDTO(user);

        userDTO.setReq_user(true);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long userId)
            throws UserException, TweetException {

        User currentUser = userService.findUserbyJWT(jwtToken);
        User reqUser = userService.findUserById(userId);

        UserDTO userDTO = UserDTO.toUserDTO(reqUser);

        // Sử dụng UserUtil để kiểm tra nếu reqUser là user hiện tại
        userDTO.setReq_user(UserUtil.isReqUser(currentUser, reqUser));

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsers(
            @RequestHeader("Authorization") String jwtToken,
            @RequestParam String query)
            throws UserException {

        // Tìm kiếm người dùng dựa trên từ khóa query
        List<User> users = userService.searchUser(query);

        // Chuyển đổi danh sách User thành UserDTO
        List<UserDTO> userDTOs = UserDTOMapper.toUserDTOs(users);

        // Trả về danh sách người dùng với HTTP status ACCEPTED
        return new ResponseEntity<>(userDTOs, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUserProfile(
            @RequestHeader("Authorization") String jwtToken, @RequestBody UserDTO userDTO)
            throws UserException {

        User user = userService.findUserbyJWT(jwtToken);

        // Cập nhật thông tin người dùng
        user = userService.updateUser(user.getId(), user);

        // Chuyển đổi User thành UserDTO
        UserDTO updatedUserDTO = UserDTO.toUserDTO(user);

        return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
    }

    @PostMapping("/{userId}/follow")
    public ResponseEntity<UserDTO> followUser(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long userId)
            throws UserException {

        User user = userService.findUserbyJWT(jwtToken);
        User followedUser = userService.followUser(userId, user);

        UserDTO userDTO = UserDTO.toUserDTO(followedUser);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }





}
