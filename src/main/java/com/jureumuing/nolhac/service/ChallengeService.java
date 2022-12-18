package com.jureumuing.nolhac.service;

import com.jureumuing.nolhac.dto.ChallengeDetail;
import com.jureumuing.nolhac.dto.ChallengePostingRes;
import com.jureumuing.nolhac.dto.ChallengeRes;
import com.jureumuing.nolhac.entity.ChallengeEntity;
import com.jureumuing.nolhac.entity.UserEntity;
import com.jureumuing.nolhac.repository.ChallengePostingRepository;
import com.jureumuing.nolhac.repository.ChallengeRepository;
import com.jureumuing.nolhac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengePostingRepository challengePostingRepository;
    private final ChallengePostingService challengePostingService;
    private final UserRepository userRepository;

    //챌린지전체목록조회
    public List<ChallengeEntity> findChallengeList(){
        List<ChallengeEntity> challengeEntities = challengeRepository.selectAll();
        return challengeEntities;
    }

    //챌린지 상세조회 (방법+참여리스트)
    public ChallengeDetail findChallengeDetail(int challengeId){
        ChallengeEntity challengeEntity = challengeRepository.select(challengeId);
        UserEntity writer = userRepository.select(challengeEntity.getUserId());
        ChallengeRes challengeRes = ChallengeRes.builder()
                .challengeId(challengeId)
                .nickname(writer.getNickname())
                .title(challengeEntity.getTitle())
                .when(challengeEntity.getWhen())
                .where(challengeEntity.getWhere())
                .what(challengeEntity.getWhat())
                .how(challengeEntity.getHow())
                .count(challengeEntity.getCount())
                .build();

        List<ChallengePostingRes> challengePostingResList = challengePostingService.findChallengePostingListByChallengeId(challengeId);


        ChallengeDetail challengeDetail = ChallengeDetail.builder()
                .challengeInfo(challengeRes)
                .challengePostingList(challengePostingResList)
                .build();

        return challengeDetail;
    }


    //챌린지 전체상세조회 (방법+참여리스트)
    public List<ChallengeDetail> findChallengeDetailAll(){
        List<ChallengeEntity> challengeEntityList = challengeRepository.selectAll();
        List<ChallengeDetail> challengeDetails = new ArrayList<>();
        for (ChallengeEntity challengeEntity: challengeEntityList) {
            UserEntity writer = userRepository.select(challengeEntity.getUserId());
            ChallengeRes challengeRes = ChallengeRes.builder()
                    .challengeId(challengeEntity.getChallengeId())
                    .nickname(writer.getNickname())
                    .title(challengeEntity.getTitle())
                    .when(challengeEntity.getWhen())
                    .where(challengeEntity.getWhere())
                    .what(challengeEntity.getWhat())
                    .how(challengeEntity.getHow())
                    .count(challengeEntity.getCount())
                    .build();
            List<ChallengePostingRes> challengePostingResList = challengePostingService.findChallengePostingListByChallengeId(challengeEntity.getChallengeId());
            ChallengeDetail challengeDetail = ChallengeDetail.builder()
                    .challengeInfo(challengeRes)
                    .challengePostingList(challengePostingResList)
                    .build();
            challengeDetails.add(challengeDetail);
        }

        return challengeDetails;
    }
}
