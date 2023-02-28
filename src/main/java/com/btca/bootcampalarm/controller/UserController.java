package com.btca.bootcampalarm.controller;

import com.btca.bootcampalarm.dto.AuthenticationRequestDto;
import com.btca.bootcampalarm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> deleteUser(@RequestBody AuthenticationRequestDto requestDto) {
        userService.deleteUser(requestDto.getMail(), requestDto.getCode());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
