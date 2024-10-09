package com.social.backendtweet.controller;

import com.social.backendtweet.dto.LikeDTO;
import com.social.backendtweet.dto.mapper.LikeDTOMapper;
import com.social.backendtweet.dto.mapper.UserDTOMapper;
import com.social.backendtweet.exception.TweetException;
import com.social.backendtweet.exception.UserException;
import com.social.backendtweet.model.Like;
import com.social.backendtweet.model.User;
import com.social.backendtweet.service.LikeService;
import com.social.backendtweet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class LikeController {

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @PostMapping("{tweetId}/like")
    public ResponseEntity<LikeDTO> likeTweet(@PathVariable Long tweetId, @RequestHeader("Authorization") String jwtToken)
            throws UserException, TweetException {

        // Lấy thông tin user từ JWT token
        User user = userService.findUserbyJWT(jwtToken);

        // Thực hiện like tweet
        Like like = likeService.likeTweet(tweetId, user);

        // Chuyển đổi Like sang LikeDTO và trả về kết quả
        LikeDTO likeDTO = LikeDTOMapper.toLikeDTO(like, UserDTOMapper.toUserDTO(user));

        return new ResponseEntity<>(likeDTO, HttpStatus.CREATED);
    }

    @GetMapping("{tweetId}/likes")
    public ResponseEntity<List<LikeDTO>> getAllLikes(@PathVariable Long tweetId, @RequestHeader("Authorization") String jwtToken)
            throws UserException, TweetException {
        // Lấy thông tin user từ JWT token
        User user = userService.findUserbyJWT(jwtToken);

        List<Like> likes = likeService.getAllLikes(tweetId);

        // Chuyển đổi danh sách Like sang danh sách LikeDTO
        List<LikeDTO> likeDTOs = LikeDTOMapper.toLikeDTOs(likes, null);

        return new ResponseEntity<>(likeDTOs, HttpStatus.CREATED);
    }
}