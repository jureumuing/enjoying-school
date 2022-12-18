package com.jureumuing.nolhac.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ChallengePostingRes {
    private int challengePostingId;
    private int userId;
    private String nickname;
    private int challengeId;
    private String challengeTitle;
    private LocalDateTime datetime;
    private String provingImage;
    private String provingVideo;
}
