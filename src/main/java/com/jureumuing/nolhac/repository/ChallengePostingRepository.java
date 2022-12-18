package com.jureumuing.nolhac.repository;

import com.jureumuing.nolhac.entity.ChallengePostingEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChallengePostingRepository {
    List<ChallengePostingEntity> selectByChallengeId(int challengePostingId);
}
