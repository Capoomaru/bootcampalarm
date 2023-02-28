package com.btca.bootcampalarm.controller;

import com.btca.bootcampalarm.dto.AuthenticationRequestDto;
import com.btca.bootcampalarm.dto.MailRequestDto;
import com.btca.bootcampalarm.service.MailService;
import com.btca.bootcampalarm.service.UserService;
import com.btca.bootcampalarm.util.MailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mails")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;
    private final UserService userService;
    private final MailUtils mailUtils;

    @PostMapping("codes")
    public ResponseEntity<?> requestMailCode(@RequestBody MailRequestDto requestDto) {

        Boolean validation = requestDto.getIsNew();
        validation = Boolean.logicalXor(validation, userService.duplicationCheck(requestDto.getMail()));
        if(!validation) return ResponseEntity.badRequest().build();

        if(!mailUtils.mailFormatCheck(requestDto.getMail()))
            throw new IllegalArgumentException("이메일 형식이 아닙니다");

        int code = mailService.sendCodeMail(requestDto.getMail());
        mailService.updateMailCode(requestDto.getMail(), code);

        return ResponseEntity.ok().build();
    }

    @PostMapping("authorize")
    public ResponseEntity<?> authenticationMailCode(@RequestBody AuthenticationRequestDto authRequest) {
        if(!mailUtils.mailFormatCheck(authRequest.getMail()))
            throw new IllegalArgumentException("이메일 형식이 아닙니다");

        mailService.validateCode(authRequest.getMail(), authRequest.getCode());

        return ResponseEntity.ok().build();
    }
}
