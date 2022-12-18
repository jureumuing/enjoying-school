package com.jureumuing.nolhac.service;

import com.jureumuing.nolhac.dto.AdmitRequestDto;
import com.jureumuing.nolhac.entity.AdmitEntity;
import com.jureumuing.nolhac.entity.CandidateEntity;
import com.jureumuing.nolhac.entity.ChallengeEntity;
import com.jureumuing.nolhac.repository.AdmitRepository;
import com.jureumuing.nolhac.repository.CandidateRepository;
import com.jureumuing.nolhac.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdmitService {

    final AdmitRepository admitRepository;
    final CandidateRepository candidateRepository;
    final CandidateService candidateService;
    final ChallengeService challengeService;
    final ChallengeRepository challengeRepository;

    public int createAdmit(AdmitRequestDto admitRequestDto, int userId){
        int candidateId=admitRequestDto.getCandidateId();
        AdmitEntity e=AdmitEntity.builder()
                .candidateId(candidateId)
                .userId(userId)
                .build();

        admitRepository.create(e);
        CandidateEntity c=candidateRepository.selectById(candidateId);
        int count=c.getAdmitCount()+1;
        candidateRepository.update(candidateId,count);
        if(count>=10){
            toChallenge(c);
        }
        return 1;

    }
    public int deleteAdmit(AdmitRequestDto admitRequestDto, int userId){
        int candidateId= admitRequestDto.getCandidateId();
        AdmitEntity e=admitRepository.select(candidateId,userId);
        admitRepository.delete(e.getAdmitId());
        CandidateEntity c=candidateRepository.selectById(candidateId);
        candidateRepository.update(candidateId,c.getAdmitCount()-1);
        return 1;
    }
    public int toChallenge(CandidateEntity ce){
        ChallengeEntity ch= ChallengeEntity.builder()
                .how(ce.getHow())
                .title(ce.getTitle())
                .what(ce.getWhat())
                .when(ce.getWhen())
                .where(ce.getWhere())
                .userId(ce.getUserId())
                .exampleImage(ce.getExampleImage())
                .build();
        challengeRepository.insert(ch);
        candidateService.deleteCandidate(ce.getCandidateId());
        return 1;
    }

}
