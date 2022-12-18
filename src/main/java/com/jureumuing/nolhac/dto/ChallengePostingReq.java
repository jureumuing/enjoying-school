package com.jureumuing.nolhac.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengePostingReq {
    private String challengeTitle;
    private String when;
    private String where;
    private String what;
    private String how;
    private String provingImage;
    private String provingVideo;
}
