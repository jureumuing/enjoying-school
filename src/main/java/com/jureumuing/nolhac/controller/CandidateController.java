package com.jureumuing.nolhac.controller;

import com.jureumuing.nolhac.dto.CandidateRequestDto;
import com.jureumuing.nolhac.dto.CandidateResponseDto;
import com.jureumuing.nolhac.dto.ErrorResponse;
import com.jureumuing.nolhac.service.CandidateService;
import com.jureumuing.nolhac.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/candidates")
public class CandidateController {

    final TokenService tokenService;
    final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<?> writeCandidate(@RequestHeader(value = "Authorization") String headerToken, CandidateRequestDto candidateDto){
        String token=headerToken;
        if(token.substring(0,7).equals("Bearer ")) {
            token = headerToken.substring("Bearer ".length());
        }
        //토큰 유효성 확인

        if(token==null||!tokenService.validateToken(token)){ //토큰이 유효하지않은 경우: 지금 이 에러 발생. 토큰이 변조된 상황.
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("사용자 인증이 불가합니다. 잘못된 접근입니다."));
        }
        //토큰을 통해 사용자 정보 불러오기
        int userId = tokenService.findUserIdByJwt(token);

        //일기 생성 후, 작성된 일기 불러오기
        int candidateId=candidateService.createCandidate(candidateDto,userId);
        CandidateResponseDto CandidateDto =candidateService.findById(candidateId);
        return ResponseEntity.status(HttpStatus.CREATED).body(CandidateDto);
    }

    @GetMapping
    public ResponseEntity<?> loadAll(){
        List<CandidateResponseDto> candidateListDto=candidateService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(candidateListDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> loadById(@PathVariable(value = "id") int id){
        CandidateResponseDto cDto = candidateService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cDto);
    }

}
