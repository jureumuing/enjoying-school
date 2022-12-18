package com.jureumuing.nolhac.dto;

import com.jureumuing.nolhac.entity.ChallengeEntity;
import com.jureumuing.nolhac.entity.ChallengePostingEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChallengeDetail {
    private ChallengeEntity challengeInfo;
    private List<ChallengePostingEntity> challengePostingList;
}
