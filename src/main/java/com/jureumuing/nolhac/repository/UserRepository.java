package com.jureumuing.nolhac.repository;

import com.jureumuing.nolhac.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    int insert(UserEntity userEntity);
    UserEntity select(int userId);
    UserEntity selectByMail(String email);
    UserEntity selectByNickname(String nickname);
}
