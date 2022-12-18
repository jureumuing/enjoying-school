package com.jureumuing.nolhac.controller;

import com.jureumuing.nolhac.dto.ErrorResponse;
import com.jureumuing.nolhac.dto.LoginRes;
import com.jureumuing.nolhac.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<?> login(String email) {
        try {
            LoginRes loginRes = userService.login(email);
            return ResponseEntity.status(HttpStatus.CREATED).body(loginRes);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("오류발생"));
        }
    }
}
