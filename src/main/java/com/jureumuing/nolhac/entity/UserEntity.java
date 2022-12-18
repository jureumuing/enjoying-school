package com.jureumuing.nolhac.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserEntity {
    private int userId;
    private String nickname;
    private String mail;
    private int challengeCount;
    private int rankId;
}
