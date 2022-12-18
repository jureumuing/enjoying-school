package com.jureumuing.nolhac.service;

import com.jureumuing.nolhac.dto.LoginRes;
import com.jureumuing.nolhac.entity.UserEntity;
import com.jureumuing.nolhac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public LoginRes login(String email){
        if(userRepository.selectByEmail(email)==null){
            UserEntity newUserEntity = UserEntity.builder()
                            .email(email)
                                    .nickname(email)
                                            .challengeCount(0)
                                                    .rankId(1)
                                                            .build();

            userRepository.insert(newUserEntity);
        }

        UserEntity user = userRepository.selectByEmail(email);

        String accessToken = tokenService.createToken(user.getUserId());
        String refreshToken = tokenService.createRefreshToken();
        LoginRes loginRes = LoginRes.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return loginRes;
    }
}
