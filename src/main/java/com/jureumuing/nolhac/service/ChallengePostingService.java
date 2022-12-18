package com.jureumuing.nolhac.service;

import com.jureumuing.nolhac.dto.ChallengePostingReq;
import com.jureumuing.nolhac.dto.ChallengePostingRes;
import com.jureumuing.nolhac.entity.ChallengeEntity;
import com.jureumuing.nolhac.entity.ChallengePostingEntity;
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
public class ChallengePostingService {

    private final ChallengeRepository challengeRepository;
    private final ChallengePostingRepository challengePostingRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    //참여챌린지추가(참여챌린지추가+해당챌린지의 참여수+1)
    public ChallengePostingRes writeChallengePosting(int userId, ChallengePostingReq challengePostingReq){

        String imagePath=fileService.uploadFileProving(challengePostingReq.getProvingImage(),"image");
        String videoPath=fileService.uploadFileProving(challengePostingReq.getProvingVideo(),"video");
        UserEntity userEntity = userRepository.select(userId);
        ChallengeEntity challengeEntity = challengeRepository.selectByChallengeTitle(challengePostingReq.getChallengeTitle());
        ChallengePostingEntity challengePostingEntity = ChallengePostingEntity.builder()
                .userId(userId)
                .challengeId(challengeEntity.getChallengeId())
                .likeCount(0)
                .provingImage(imagePath)
                .provingVideo(videoPath)
                .build();

        challengePostingRepository.insert(challengePostingEntity);
        ChallengePostingEntity newChallengePostingEntity = challengePostingRepository.select(challengePostingEntity.getChallengePostingId());

        int origCount = challengeEntity.getCount();
        int updateCount = origCount+1;
        challengeRepository.updateCount(challengeEntity.getChallengeId(), updateCount);

        ChallengePostingRes challengePostingRes = ChallengePostingRes.builder()
                .challengePostingId(newChallengePostingEntity.getChallengePostingId())
                .userId(userId)
                .nickname(userEntity.getNickname())
                .challengeId(challengeEntity.getChallengeId())
                .challengeTitle(challengeEntity.getTitle())
                .datetime(newChallengePostingEntity.getDatetime())
                .provingImage(imagePath)
                .provingVideo(videoPath)
                .build();

        return challengePostingRes;
    }

    //특정챌린지포스팅조회
    public ChallengePostingRes findChallengePostingDetail(int challengePostingId){
        ChallengePostingEntity challengePostingEntity = challengePostingRepository.select(challengePostingId);
        ChallengeEntity challengeEntity = challengeRepository.select(challengePostingEntity.getChallengeId());
        UserEntity writer = userRepository.select(challengePostingEntity.getUserId());
        ChallengePostingRes challengePostingRes = ChallengePostingRes.builder()
                .challengePostingId(challengePostingEntity.getChallengePostingId())
                .userId(writer.getUserId())
                .nickname(writer.getNickname())
                .challengeId(challengeEntity.getChallengeId())
                .challengeTitle(challengeEntity.getTitle())
                .datetime(challengePostingEntity.getDatetime())
                .provingImage(challengePostingEntity.getProvingImage())
                .provingVideo(challengePostingEntity.getProvingVideo())
                .build();
        return challengePostingRes;
    }

    //특정 챌린지에 대한 챌린지포스팅목록 조회
    public List<ChallengePostingRes> findChallengePostingListByChallengeId(int challengeId){
        List<ChallengePostingEntity> challengePostingEntityList = challengePostingRepository.selectByChallengeId(challengeId);
        List<ChallengePostingRes> challengePostingResList = new ArrayList<>();
        for(ChallengePostingEntity challengePostingEntity: challengePostingEntityList){
            UserEntity writer = userRepository.select(challengePostingEntity.getUserId());
            ChallengeEntity challengeEntity = challengeRepository.select(challengePostingEntity.getChallengeId());
            ChallengePostingRes challengePostingRes = ChallengePostingRes.builder()
                    .challengePostingId(challengePostingEntity.getChallengePostingId())
                    .userId(writer.getUserId())
                    .nickname(writer.getNickname())
                    .challengeId(challengeEntity.getChallengeId())
                    .challengeTitle(challengeEntity.getTitle())
                    .datetime(challengePostingEntity.getDatetime())
                    .provingImage(challengePostingEntity.getProvingImage())
                    .provingVideo(challengePostingEntity.getProvingVideo())
                    .build();
            challengePostingResList.add(challengePostingRes);
        }
        return challengePostingResList;
    }

    //유저에 대한 챌린지포스팅목록 조회
    public List<ChallengePostingRes> findChallengePostingListByUserId(int userId){
        List<ChallengePostingEntity> challengePostingEntityList = challengePostingRepository.selectByUserId(userId);
        List<ChallengePostingRes> challengePostingResList = new ArrayList<>();
        for(ChallengePostingEntity challengePostingEntity: challengePostingEntityList){
            UserEntity writer = userRepository.select(challengePostingEntity.getUserId());
            ChallengeEntity challengeEntity = challengeRepository.select(challengePostingEntity.getChallengeId());
            ChallengePostingRes challengePostingRes = ChallengePostingRes.builder()
                    .challengePostingId(challengePostingEntity.getChallengePostingId())
                    .userId(writer.getUserId())
                    .nickname(writer.getNickname())
                    .challengeId(challengeEntity.getChallengeId())
                    .challengeTitle(challengeEntity.getTitle())
                    .datetime(challengePostingEntity.getDatetime())
                    .provingImage(challengePostingEntity.getProvingImage())
                    .provingVideo(challengePostingEntity.getProvingVideo())
                    .build();
            challengePostingResList.add(challengePostingRes);
        }
        return challengePostingResList;
    }

    public List<ChallengePostingRes> findChallengePostingAll(){
        List<ChallengePostingEntity> challengePostingEntityList = challengePostingRepository.selectAll();
        List<ChallengePostingRes> challengePostingResList = new ArrayList<>();
        for(ChallengePostingEntity challengePostingEntity: challengePostingEntityList){
            UserEntity writer = userRepository.select(challengePostingEntity.getUserId());
            ChallengeEntity challengeEntity = challengeRepository.select(challengePostingEntity.getChallengeId());
            ChallengePostingRes challengePostingRes = ChallengePostingRes.builder()
                    .challengePostingId(challengePostingEntity.getChallengePostingId())
                    .userId(writer.getUserId())
                    .nickname(writer.getNickname())
                    .challengeId(challengeEntity.getChallengeId())
                    .challengeTitle(challengeEntity.getTitle())
                    .datetime(challengePostingEntity.getDatetime())
                    .provingImage(challengePostingEntity.getProvingImage())
                    .provingVideo(challengePostingEntity.getProvingVideo())
                    .build();
            challengePostingResList.add(challengePostingRes);
        }
        return challengePostingResList;
    }
}
