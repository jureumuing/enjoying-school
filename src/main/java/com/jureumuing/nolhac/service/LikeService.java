package com.jureumuing.nolhac.service;

import com.jureumuing.nolhac.entity.ChallengePostingEntity;
import com.jureumuing.nolhac.entity.LikeEntity;
import com.jureumuing.nolhac.repository.ChallengePostingRepository;
import com.jureumuing.nolhac.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final ChallengePostingRepository challengePostingRepository;

    public int addLike(int userId, int challengePostingId){
        LikeEntity likeEntity = LikeEntity.builder()
                .userId(userId)
                .challengePostingId(challengePostingId)
                .build();
        likeRepository.insert(likeEntity);

        ChallengePostingEntity challengePostingEntity = challengePostingRepository.select(challengePostingId);
        int origLikeCount = challengePostingEntity.getLikeCount();
        challengePostingEntity.setLikeCount(origLikeCount+1);
        int updateLikeCount = challengePostingEntity.getLikeCount();
        challengePostingRepository.update(challengePostingEntity);
        return updateLikeCount;
    }
}
