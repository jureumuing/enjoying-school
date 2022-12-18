package com.jureumuing.nolhac.service;

import com.jureumuing.nolhac.entity.ChallengeEntity;
import com.jureumuing.nolhac.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    public List<ChallengeEntity> findChallengeList(){
        List<ChallengeEntity> challengeEntities = challengeRepository.selectAll();
        return challengeEntities;
    }
}
