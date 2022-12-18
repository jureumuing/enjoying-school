package com.jureumuing.nolhac.dto;

import com.jureumuing.nolhac.entity.ChallengeEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChallengeDetail {
    private ChallengeEntity challengeEntity;
}
