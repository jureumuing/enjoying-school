package com.jureumuing.nolhac.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeRes {
    private int challengeId;
    private String title;
    private String nickname;
    private String when;
    private String where;
    private String what;
    private String how;
    private int count;
}
