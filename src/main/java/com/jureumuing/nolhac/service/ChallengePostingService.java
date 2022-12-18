package com.jureumuing.nolhac.service;

import com.jureumuing.nolhac.dto.ChallengePostingReq;
import com.jureumuing.nolhac.dto.ChallengePostingRes;
import com.jureumuing.nolhac.entity.ChallengeEntity;
import com.jureumuing.nolhac.entity.ChallengePostingEntity;
import com.jureumuing.nolhac.entity.UserEntity;
import com.jureumuing.nolhac.repository.ChallengePostingRepository;
import com.jureumuing.nolhac.repository.ChallengeRepository;
import com.jureumuing.nolhac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChallengePostingService {

    private final ChallengeRepository challengeRepository;
    private final ChallengePostingRepository challengePostingRepository;
    private final UserRepository userRepository;

    //참여챌린지추가(참여챌린지추가+해당챌린지의 참여수+1)
    public ChallengePostingRes writeChallengePosting(int userId, ChallengePostingReq challengePostingReq){
        UserEntity userEntity = userRepository.select(userId);
        ChallengeEntity challengeEntity = challengeRepository.selectByChallengeTitle(challengePostingReq.getChallengeTitle());
        ChallengePostingEntity challengePostingEntity = ChallengePostingEntity.builder()
                .userId(userId)
                .challengeId(challengeEntity.getChallengeId())
                .likeCount(0)
                .provingImage(challengePostingReq.getProvingImage())
                .provingVideo(challengePostingReq.getProvingVideo())
                .build();

        challengePostingRepository.insert(challengePostingEntity);
        ChallengePostingEntity newChallengePostingEntity = challengePostingRepository.select(challengePostingEntity.getChallengePostingId());

        int origCount = challengeEntity.getCount();
        int updateCount = origCount+1;
        challengeRepository.updateCount(challengeEntity.getChallengeId(), updateCount);

        ChallengePostingRes challengePostingRes = ChallengePostingRes.builder()
                .challengePostingId(newChallengePostingEntity.getChallengePostingId())
                .userId(userId)
                .nickname(userEntity.getNickname())
                .challengeId(challengeEntity.getChallengeId())
                .challengeTitle(challengeEntity.getTitle())
                .datetime(newChallengePostingEntity.getDatetime())
                .what(challengePostingReq.getWhat())
                .when(challengePostingReq.getWhen())
                .where(challengePostingReq.getWhere())
                .how(challengePostingReq.getHow())
                .provingImage(challengePostingReq.getProvingImage())
                .provingVideo(challengePostingReq.getProvingVideo())
                .build();

        return challengePostingRes;
    }
}
