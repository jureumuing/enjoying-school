package com.jureumuing.nolhac.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeEntity {
    private int likeId;
    private int userId;
    private int challengePostingId;
}
