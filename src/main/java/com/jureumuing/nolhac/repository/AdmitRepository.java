package com.jureumuing.nolhac.repository;

import com.jureumuing.nolhac.entity.AdmitEntity;
import com.jureumuing.nolhac.entity.CandidateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdmitRepository {

    int create(AdmitEntity admitEntity);
    AdmitEntity selectByCandidate(int admitId);
    AdmitEntity select(int candidateId, int userId);
    int delete(int admitId);
    int deleteCandidate(int candidateId);
}
