package com.jureumuing.nolhac.service;

import com.jureumuing.nolhac.dto.ChallengePostingRes;
import com.jureumuing.nolhac.dto.LoginRes;
import com.jureumuing.nolhac.entity.UserEntity;
import com.jureumuing.nolhac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final ChallengePostingService challengePostingService;

    public LoginRes login(String mail){
        if(userRepository.selectByMail(mail)==null){
            UserEntity newUserEntity = UserEntity.builder()
                            .mail(mail)
                                    .nickname(mail)
                                            .challengeCount(0)
                                                    .rankId(1)
                                                            .build();

            userRepository.insert(newUserEntity);
        }

        UserEntity user = userRepository.selectByMail(mail);

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

    public List<ChallengePostingRes> findMyChallengePostingList(int userId){
        List<ChallengePostingRes> challengePostingResList = challengePostingService.findChallengePostingListByUserId(userId);
        return challengePostingResList;
    }
}
