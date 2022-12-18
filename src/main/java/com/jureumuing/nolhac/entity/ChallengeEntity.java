package com.jureumuing.nolhac.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeEntity {
    private int challengeId;
    private String title;
    private String when;
    private String what;
    private String how;
    private String where;
    private String exampleImage;
    private int count;
    private int userId;
}
