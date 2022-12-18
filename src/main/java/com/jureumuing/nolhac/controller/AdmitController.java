package com.jureumuing.nolhac.controller;

import com.jureumuing.nolhac.dto.AdmitRequestDto;
import com.jureumuing.nolhac.dto.CandidateRequestDto;
import com.jureumuing.nolhac.dto.CandidateResponseDto;
import com.jureumuing.nolhac.dto.ErrorResponse;
import com.jureumuing.nolhac.service.AdmitService;
import com.jureumuing.nolhac.service.CandidateService;
import com.jureumuing.nolhac.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admits")
public class AdmitController {
    final CandidateService candidateService;
    final AdmitService admitService;
    final TokenService tokenService;




    @PostMapping
    public ResponseEntity<?> createAdmit(@RequestHeader(value = "Authorization") String headerToken, AdmitRequestDto admitRequestDto){
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

        if (admitService.createAdmit(admitRequestDto, userId)==1){
            return ResponseEntity.status(HttpStatus.CREATED).body("인정 추가");}
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("실패");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAdmit(@RequestHeader(value = "Authorization") String headerToken, AdmitRequestDto admitRequestDto){
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

        if (admitService.deleteAdmit(admitRequestDto, userId)==1){
            return ResponseEntity.status(HttpStatus.CREATED).body("인정 삭제");}
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("실패");
        }
    }

}
