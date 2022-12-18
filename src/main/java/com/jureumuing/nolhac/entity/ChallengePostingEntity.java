package com.jureumuing.nolhac.entity;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
public class ChallengePostingEntity {
    private int challengePostingId;
    private int challengeId;
    private LocalDateTime datetime;
    private String provingImage;
    private String provingVideo;
    private int likeCount;
    private int userId;
}
