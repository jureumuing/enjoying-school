package com.jureumuing.nolhac.controller;

import com.jureumuing.nolhac.dto.ErrorResponse;
import com.jureumuing.nolhac.entity.CandidateEntity;
import com.jureumuing.nolhac.entity.ChallengeEntity;
import com.jureumuing.nolhac.repository.ChallengeRepository;
import com.jureumuing.nolhac.service.ChallengeService;
import com.jureumuing.nolhac.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChallengeController {

    private final TokenService tokenService;
    private final ChallengeService challengeService;

    //챌린지종류 전체 목록 조회 api
    @GetMapping("/api/challenges")
    public ResponseEntity<?> loadChallengeList(@RequestHeader(value = "Authorization") String headerToken) {
        String token = headerToken;
        if (token.substring(0, 7).equals("Bearer ")) {
            token = headerToken.substring("Bearer ".length());
        }
        int userId = tokenService.findUserIdByJwt(token);
        if (token == null || !tokenService.validateToken(token))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("토큰 인증 실패. 조회 권한이 없습니다."));
        try {
            List<ChallengeEntity> challengeEntityList = challengeService.findChallengeList();
            return ResponseEntity.status(HttpStatus.OK).body(challengeEntityList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("오류발생"));
        }
    }

    @GetMapping("/api/challenges/{challengeId}")
    public ResponseEntity<?> findChallengeDetail(@RequestHeader(value = "Authorization") String headerToken, @PathVariable int challengeId) {
        String token = headerToken;
        if (token.substring(0, 7).equals("Bearer ")) {
            token = headerToken.substring("Bearer ".length());
        }
        int userId = tokenService.findUserIdByJwt(token);
        if (token == null || !tokenService.validateToken(token))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("토큰 인증 실패. 조회 권한이 없습니다."));

        try {
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("오류발생"));
        }
    }
}
