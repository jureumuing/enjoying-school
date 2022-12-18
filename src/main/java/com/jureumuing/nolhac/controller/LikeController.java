package com.jureumuing.nolhac.controller;

import com.jureumuing.nolhac.dto.ErrorResponse;
import com.jureumuing.nolhac.service.LikeService;
import com.jureumuing.nolhac.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final TokenService tokenService;
    private final LikeService likeService;

    @PostMapping("/api/likes")
    public ResponseEntity<?> addLike(@RequestHeader(value = "Authorization")String headerToken, @RequestParam int challengePostingId) {
        String token = headerToken;
        if (token.substring(0, 7).equals("Bearer ")) {
            token = headerToken.substring("Bearer ".length());
        }
        int userId = tokenService.findUserIdByJwt(token);
        if (token == null || !tokenService.validateToken(token))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("토큰 인증 실패. 조회 권한이 없습니다."));

        try {
            int likeCount = likeService.addLike(userId, challengePostingId);
            return ResponseEntity.status(HttpStatus.CREATED).body("바뀐 좋아요 수: " + likeCount);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("오류발생"));
        }
    }
}
