package com.jureumuing.nolhac.controller;

import com.jureumuing.nolhac.dto.ChallengePostingReq;
import com.jureumuing.nolhac.dto.ChallengePostingRes;
import com.jureumuing.nolhac.dto.ErrorResponse;
import com.jureumuing.nolhac.service.ChallengePostingService;
import com.jureumuing.nolhac.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChallengePostingController {
    private final TokenService tokenService;
    private final ChallengePostingService challengePostingService;

    //참여한 챌린지 등록
    @PostMapping("/api/challenge-posting")
    public ResponseEntity<?> writeChallengePosting(@RequestHeader(value = "Authorization") String headerToken, @RequestBody ChallengePostingReq challengePostingReq) {
        String token = headerToken;
        if (token.substring(0, 7).equals("Bearer ")) {
            token = headerToken.substring("Bearer ".length());
        }
        int userId = tokenService.findUserIdByJwt(token);
        if (token == null || !tokenService.validateToken(token))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("토큰 인증 실패. 조회 권한이 없습니다."));

        try {
            ChallengePostingRes challengePostingRes = challengePostingService.writeChallengePosting(userId, challengePostingReq);
            return ResponseEntity.status(HttpStatus.CREATED).body(challengePostingRes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("오류발생"));
        }
    }

    //특정 챌린지포스팅 조회
    @GetMapping("/api/challenge-posting/{challengePostingId}")
    public ResponseEntity<?> findChallengePostingDetail(@RequestHeader(value = "Authorization") String headerToken, @PathVariable int challengePostingId) {
        String token = headerToken;
        if (token.substring(0, 7).equals("Bearer ")) {
            token = headerToken.substring("Bearer ".length());
        }
        int userId = tokenService.findUserIdByJwt(token);
        if (token == null || !tokenService.validateToken(token))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("토큰 인증 실패. 조회 권한이 없습니다."));

        try {
            ChallengePostingRes challengePostingRes = challengePostingService.findChallengePostingDetail(challengePostingId);
            return ResponseEntity.status(HttpStatus.OK).body(challengePostingRes);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류 발생");
        }
    }
}
