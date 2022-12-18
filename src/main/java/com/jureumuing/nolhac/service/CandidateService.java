package com.jureumuing.nolhac.service;


import com.jureumuing.nolhac.controller.CandidateController;
import com.jureumuing.nolhac.dto.CandidateRequestDto;
import com.jureumuing.nolhac.dto.CandidateResponseDto;
import com.jureumuing.nolhac.entity.CandidateEntity;
import com.jureumuing.nolhac.repository.AdmitRepository;
import com.jureumuing.nolhac.repository.CandidateRepository;
import com.jureumuing.nolhac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {
    
    final CandidateRepository candidateRepository;
    final UserRepository userRepository;
    final AdmitRepository admitRepository;
    final FileService fileService;

    public List<CandidateResponseDto> findAll(){
        List<CandidateEntity> candidateEntities =candidateRepository.selectAll();
        List<CandidateResponseDto> candidateList= new ArrayList<>();
        for(CandidateEntity e: candidateEntities){
            CandidateResponseDto candidateResponseDto = CandidateResponseDto.builder()
                    .candidateId(e.getCandidateId())
                    .admitCount(e.getAdmitCount())
                    .how(e.getHow())
                    .title(e.getTitle())
                    .what(e.getWhat())
                    .when(e.getWhen())
                    .where(e.getWhere())
                    .writer(userRepository.select(e.getUserId()).getNickname())
                    .exampleImage(e.getExampleImage())
                    .build();
            candidateList.add(candidateResponseDto);
        }
        return candidateList;
    }

    public int createCandidate(CandidateRequestDto e, int writer){
        String path=fileService.uploadFileExemple(e.getExampleImage());
        CandidateEntity newCandidateEntity= CandidateEntity.builder()
                .how(e.getHow())
                .title(e.getTitle())
                .what(e.getWhat())
                .when(e.getWhen())
                .where(e.getWhere())
                .userId(writer)
                .exampleImage(path)
                .build();
        candidateRepository.insert(newCandidateEntity);
        return 1;
    }
    public CandidateResponseDto findById(int id){
        CandidateEntity e =candidateRepository.selectById(id);
        CandidateResponseDto candidateResponseDto = CandidateResponseDto.builder()
                    .candidateId(e.getCandidateId())
                    .admitCount(e.getAdmitCount())
                    .how(e.getHow())
                    .title(e.getTitle())
                    .what(e.getWhat())
                    .when(e.getWhen())
                    .where(e.getWhere())
                    .exampleImage(e.getExampleImage())
                    .writer(userRepository.select(e.getUserId()).getNickname())
                    .build();

        return candidateResponseDto;
    }

    public int deleteCandidate(int id){
        candidateRepository.delete(id);
        admitRepository.deleteCandidate(id);
        return 1;
    }
}
