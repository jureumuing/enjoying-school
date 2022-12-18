package com.jureumuing.nolhac.service;

import com.jureumuing.nolhac.dto.ChallengeDetail;
import com.jureumuing.nolhac.entity.ChallengeEntity;
import com.jureumuing.nolhac.entity.ChallengePostingEntity;
import com.jureumuing.nolhac.repository.ChallengePostingRepository;
import com.jureumuing.nolhac.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final ChallengePostingRepository challengePostingRepository;

    //챌린지전체목록조회
    public List<ChallengeEntity> findChallengeList(){
        List<ChallengeEntity> challengeEntities = challengeRepository.selectAll();
        return challengeEntities;
    }

    //챌린지 상세조회 (방법+참여리스트)
    public ChallengeDetail findChallengeDetail(int challengeId){
        ChallengeEntity challengeEntity = challengeRepository.select(challengeId);
        List<ChallengePostingEntity> challengePostingEntityList = challengePostingRepository.selectByChallengeId(challengeId);

        ChallengeDetail challengeDetail = ChallengeDetail.builder()
                .challengeInfo(challengeEntity)
                .challengePostingList(challengePostingEntityList)
                .build();

        return challengeDetail;
    }
}
