package com.social.backendtweet.service.Imp;

import com.social.backendtweet.config.JWTProvider;
import com.social.backendtweet.exception.UserException;
import com.social.backendtweet.dto.TweetDTO;
import com.social.backendtweet.model.Tweet;
import com.social.backendtweet.model.User;
import com.social.backendtweet.reposity.UserRepository;
import com.social.backendtweet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTProvider jwtProvider;

    public ResponseEntity<TweetDTO> createTweet(TweetDTO tweetDTO, User user) throws UserException {
        return null;
    }

    @Override
    public User findUserById(Long userId) throws UserException {
        return userRepository.findById(userId).orElseThrow(() -> new UserException("User not found"));
    }

    @Override
    public User findUserbyJWT(String jwt) throws UserException {
        String usernameOrEmail = jwtProvider.getEmailFromToken(jwt);

        if (usernameOrEmail == null) {
            throw new UserException("User not found");
        }

        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if (user == null) {
            throw new UserException("User not found");
        }

        return user;
    }

    @Override
    public User updateUser(Long userId, User updatedUser) throws UserException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found"));

        user.setFullName(updatedUser.getFullName());
        user.setBio(updatedUser.getBio());
        user.setAvatar(updatedUser.getAvatar());
        user.setCoverImage(updatedUser.getCoverImage());

        return userRepository.save(user);
    }

    @Override
    public User followUser(Long userId, User user) throws UserException {
        // Tìm người dùng cần được theo dõi theo userId
        User followedUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));

        // Kiểm tra xem người dùng hiện tại đã theo dõi người dùng này chưa
        if (followedUser.getFollowers().contains(user)) {
            throw new UserException("You are already following this user");
        }

        // Thêm user vào danh sách followers của followedUser
        followedUser.getFollowers().add(user);

        // Thêm followedUser vào danh sách following của user hiện tại
        user.getFollowing().add(followedUser);

        // Lưu cả hai đối tượng
        userRepository.save(followedUser);
        userRepository.save(user);

        return followedUser;
    }


    @Override
    public List<User> searchUser(String query) throws UserException {
        // Thực hiện tìm kiếm người dùng theo query (username hoặc email)
        List<User> users = userRepository.searchUser(query);

        // Kiểm tra nếu không có người dùng nào được tìm thấy
        if (users.isEmpty()) {
            throw new UserException("No users found for the search query: " + query);
        }

        return users;
    }

}