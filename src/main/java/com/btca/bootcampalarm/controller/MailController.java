package com.btca.bootcampalarm.controller;

import com.btca.bootcampalarm.dto.AuthenticationRequestDto;
import com.btca.bootcampalarm.dto.MailRequestDto;
import com.btca.bootcampalarm.service.MailService;
import com.btca.bootcampalarm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mails")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final UserService userService;

    @PostMapping("codes")
    public ResponseEntity<?> requestEmailCode(@RequestBody MailRequestDto requestDto) {

        Boolean validation = requestDto.getIsNew();
        validation = Boolean.logicalXor(validation, userService.duplicationCheck(requestDto.getMail()));
        if(!validation) return ResponseEntity.badRequest().build();

        if(!mailService.mailFormatCheck(requestDto.getMail()))
            throw new IllegalArgumentException("이메일 형식이 아닙니다");

        int code = mailService.sendCodeMail(requestDto.getMail());
        mailService.updateMailCode(requestDto.getMail(), code);

        return ResponseEntity.ok().build();
    }

    @PostMapping("authorize")
    public ResponseEntity<?> authenticationEmailCode(@RequestBody AuthenticationRequestDto authRequest) {
        if(!mailService.mailFormatCheck(authRequest.getMail()))
            throw new IllegalArgumentException("이메일 형식이 아닙니다");

        mailService.validateCode(authRequest.getMail(), authRequest.getCode());

        return ResponseEntity.ok().build();
    }

    @PostMapping("save")
    public ResponseEntity<?> saveForm(@RequestBody AuthenticationRequestDto userRequest) {



        return ResponseEntity.ok().build();
    }
}
