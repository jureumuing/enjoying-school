package com.jureumuing.nolhac.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChallengePostingEntity {
    private int challengePostingId;
    private int challengeId;
    private LocalDateTime dateTime;
    private String provingImage;
    private String provingVideo;
    private int likeCount;
    private int userId;
}
