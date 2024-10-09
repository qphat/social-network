package com.social.backendtweet.dto.mapper;

import com.social.backendtweet.dto.LikeDTO;
import com.social.backendtweet.dto.TweetDTO;
import com.social.backendtweet.dto.UserDTO;
import com.social.backendtweet.model.Like;
import com.social.backendtweet.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class LikeDTOMapper {

    public static LikeDTO toLikeDTO(Like like, UserDTO reqUserDTO) {
        // Chuyển đổi người dùng và yêu cầu người dùng sang UserDTO
        UserDTO userDTO = UserDTOMapper.toUserDTO(like.getUser());

        // Chuyển đổi tweet sang TweetDTO
        TweetDTO tweetDTO = TweetDTOMapper.toTweetDTO(like.getTweet(), reqUserDTO);

        // Tạo đối tượng LikeDTO và gán các thuộc tính
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.setId(like.getId());
        likeDTO.setUser(userDTO);
        likeDTO.setTweet(tweetDTO);

        return likeDTO;
    }

    // Phương thức chuyển đổi từ danh sách Like sang danh sách LikeDTO
    public static List<LikeDTO> toLikeDTOs(List<Like> likes, User reqUser) {
        // Mỗi phần tử Like sẽ được chuyển đổi bằng cách gọi phương thức toLikeDTO
        UserDTO reqUserDTO = UserDTOMapper.toUserDTO(reqUser); // Chuyển đổi User thành UserDTO
        return likes.stream()
                .map(like -> toLikeDTO(like, reqUserDTO))
                .collect(Collectors.toList());
    }


}

