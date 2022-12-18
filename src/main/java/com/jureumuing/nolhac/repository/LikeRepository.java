package com.jureumuing.nolhac.repository;

import com.jureumuing.nolhac.entity.LikeEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeRepository {
    int insert(LikeEntity likeEntity);
}
