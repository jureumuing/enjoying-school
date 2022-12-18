package com.jureumuing.nolhac.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserEntity {
    private int userId;
    private String email;
    private String nickname;
    private int challengeCount;
    private int rankId;
}
