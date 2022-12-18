package com.jureumuing.nolhac.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChallengeDetail {
    private ChallengeRes challengeInfo;
    private List<ChallengePostingRes> challengePostingList;
}
