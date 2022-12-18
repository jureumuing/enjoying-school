package com.jureumuing.nolhac.repository;

import com.jureumuing.nolhac.controller.CandidateController;
import com.jureumuing.nolhac.entity.CandidateEntity;
import com.jureumuing.nolhac.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Mapper
public interface CandidateRepository {

    int insert(CandidateEntity candidateEntity);
    CandidateEntity selectById(int candidateId);
    int delete(int candidateId);
    ArrayList<CandidateEntity> selectAll();
    int update(int candidateId, int admitCount);

}
