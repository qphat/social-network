package com.social.backendtweet.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    private String content;

    @OneToMany(mappedBy = "replyForTweet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tweet> replyTweet = new ArrayList<>();

    @ManyToMany(mappedBy = "reTweetTweet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> reTweetUser = new ArrayList<>();

    @ManyToOne
    private Tweet replyForTweet;

    private boolean isReTweet;

    private boolean isReply;

    private LocalDateTime createdAt;

    private String image;
    private String video;

    @OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Like> likes = new ArrayList<>();


}
