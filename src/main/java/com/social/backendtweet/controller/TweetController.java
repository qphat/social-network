package com.social.backendtweet.controller;


import com.social.backendtweet.dto.TweetDTO;
import com.social.backendtweet.dto.mapper.TweetDTOMapper;
import com.social.backendtweet.dto.mapper.UserDTOMapper;
import com.social.backendtweet.exception.TweetException;
import com.social.backendtweet.exception.UserException;
import com.social.backendtweet.model.Tweet;
import com.social.backendtweet.model.User;
import com.social.backendtweet.response.ApiResponse;
import com.social.backendtweet.service.TweetService;
import com.social.backendtweet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tweet")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<TweetDTO> createTweet(@RequestBody Tweet tweet, @RequestHeader("Authorization") String jwtToken) throws UserException {
        // Lấy thông tin user từ JWT token
        User user = userService.findUserbyJWT(jwtToken);

        // Tạo tweet mới
        Tweet newTweet = tweetService.createTweet(tweet, user);

        // Chuyển đổi tweet mới sang TweetDTO
        TweetDTO tweetDTO = TweetDTOMapper.toTweetDTO(newTweet, UserDTOMapper.toUserDTO(user));

        // Trả về ResponseEntity với trạng thái CREATED
        return ResponseEntity.status(HttpStatus.CREATED).body(tweetDTO);
    }

    @DeleteMapping("/{tweetId}")
    public ResponseEntity<ApiResponse> deleteTweet(@PathVariable Long tweetId, @RequestHeader("Authorization") String jwtToken)
            throws UserException, TweetException {

        // Lấy thông tin user từ JWT token
        User user = userService.findUserbyJWT(jwtToken);

        // Gọi phương thức deleteTweetById
        try {
            tweetService.deleteTweetById(tweetId);
        } catch (TweetException e) {
            // Trả về phản hồi lỗi nếu không thể xóa tweet
            ApiResponse errorResponse = new ApiResponse();
            errorResponse.setMessage(e.getMessage());
            errorResponse.setStatus(false);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }

        // Tạo phản hồi thành công
        ApiResponse response = new ApiResponse();
        response.setMessage("Tweet deleted successfully");
        response.setStatus(true);

        // Trả về ResponseEntity với trạng thái OK và đối tượng ApiResponse
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<TweetDTO>> getAllTweets( @RequestHeader("Authorization") String jwtToken)
            throws UserException, TweetException {

        // Lấy thông tin user từ JWT token
        User user = userService.findUserbyJWT(jwtToken);

        // Lấy tất cả các tweet
        List<Tweet> tweets = tweetService.findAllTweets();

        // Chuyển đổi sang List<TweetDTO>
        List<TweetDTO> tweetDTOs = tweets.stream()
                .map(tweet -> TweetDTOMapper.toTweetDTO(tweet, UserDTOMapper.toUserDTO(user)))
                .collect(Collectors.toList());

        return new ResponseEntity<>(tweetDTOs, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TweetDTO>> getUserAllTweets(@PathVariable Long userId, @RequestHeader("Authorization") String jwtToken)
            throws UserException, TweetException {

        // Lấy thông tin user từ JWT token
        User requestUser = userService.findUserbyJWT(jwtToken);

        // Lấy tất cả các tweet của người dùng theo userId
        List<Tweet> tweets = tweetService.getUserTweets(userId);

        // Chuyển đổi sang List<TweetDTO>
        List<TweetDTO> tweetDTOs = tweets.stream()
                .map(tweet -> TweetDTOMapper.toTweetDTO(tweet, UserDTOMapper.toUserDTO(requestUser)))
                .collect(Collectors.toList());

        return new ResponseEntity<>(tweetDTOs, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/likes")
    public ResponseEntity<List<TweetDTO>> findTweetByLikesContainerUser(@PathVariable Long userId, @RequestHeader("Authorization") String jwtToken)
            throws UserException, TweetException {

        // Lấy thông tin user từ JWT token
        User requestUser = userService.findUserbyJWT(jwtToken);

        // Lấy tất cả các tweet của người dùng theo userId
        List<Tweet> tweets = tweetService.findByLikeContainUser(userId);

        // Chuyển đổi sang List<TweetDTO>
        List<TweetDTO> tweetDTOs = tweets.stream()
                .map(tweet -> TweetDTOMapper.toTweetDTO(tweet, UserDTOMapper.toUserDTO(requestUser)))
                .collect(Collectors.toList());

        return new ResponseEntity<>(tweetDTOs, HttpStatus.OK);
    }



}
