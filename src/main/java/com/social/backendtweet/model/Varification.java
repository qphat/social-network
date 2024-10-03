package com.social.backendtweet.model;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Varification {
    private boolean status=false;
    private LocalDate startedAt;
    private LocalDate endsAt;
    private String planType;
}
