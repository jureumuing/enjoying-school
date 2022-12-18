package com.jureumuing.nolhac.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRes {
    private int userId;
    private String nickname;
    private String accessToken;
    private String refreshToken;
}
