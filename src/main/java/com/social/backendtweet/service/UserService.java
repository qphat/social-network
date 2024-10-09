package com.social.backendtweet.service;

import com.social.backendtweet.exception.UserException;
import com.social.backendtweet.model.User;

import java.util.List;

public interface UserService {

    public User findUserById(Long userId)  throws UserException;
    public User findUserbyJWT(String jwt) throws UserException;
    public User updateUser(Long userId, User user) throws UserException;
    public User followUser(Long userId, User user) throws UserException;
    public List<User> searchUser(String query) throws UserException;


}
