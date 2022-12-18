package com.jureumuing.nolhac.repository;

import com.jureumuing.nolhac.entity.ChallengeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChallengeRepository {
    int insert(ChallengeEntity challengeEntity);
    ChallengeEntity select(int challengeId);
    ChallengeEntity selectByChallengeTitle(String challengeTitle);
    int updateCount(int challengeId, int count);
    List<ChallengeEntity> selectAll();
}
