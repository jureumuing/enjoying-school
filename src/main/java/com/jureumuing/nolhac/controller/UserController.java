package com.jureumuing.nolhac.controller;

import com.jureumuing.nolhac.dto.ChallengePostingRes;
import com.jureumuing.nolhac.dto.ErrorResponse;
import com.jureumuing.nolhac.dto.LoginRes;
import com.jureumuing.nolhac.dto.MailReq;
import com.jureumuing.nolhac.service.TokenService;
import com.jureumuing.nolhac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping("/api/users")
    public ResponseEntity<?> login(@RequestBody MailReq mailReq) {
        try {
            LoginRes loginRes = userService.login(mailReq.getMail());
            return ResponseEntity.status(HttpStatus.CREATED).body(loginRes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("오류발생"));
        }
    }

    @GetMapping("/api/users/{nickname}/challenge-posting")
    public ResponseEntity<?> loadMyChallengePostingList(@RequestHeader(value = "Authorization") String headerToken, @PathVariable String nickname) {
        String token = headerToken;
        if (token.substring(0, 7).equals("Bearer ")) {
            token = headerToken.substring("Bearer ".length());
        }
        int userId = tokenService.findUserIdByJwt(token);
        if (token == null || !tokenService.validateToken(token))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("토큰 인증 실패. 조회 권한이 없습니다."));

        try {
            List<ChallengePostingRes> challengePostingResList = userService.findMyChallengePostingList(userId);
            return ResponseEntity.status(HttpStatus.OK).body(challengePostingResList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류 발생");
        }
    }
}
