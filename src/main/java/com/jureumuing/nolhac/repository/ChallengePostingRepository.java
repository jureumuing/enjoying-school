package com.jureumuing.nolhac.repository;

import com.jureumuing.nolhac.entity.ChallengePostingEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChallengePostingRepository {

    int insert(ChallengePostingEntity challengePostingEntity);
    ChallengePostingEntity select(int challengePostingId);
    List<ChallengePostingEntity> selectByChallengeId(int challengeId);
    List<ChallengePostingEntity> selectByUserId(int userId);
    int update(ChallengePostingEntity challengePostingEntity);
}
