package com.jureumuing.nolhac.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserEntity {
    private int userId;
    private String mail;
    private String nickname;
    private int challengeCount;
    private int rankId;
}
