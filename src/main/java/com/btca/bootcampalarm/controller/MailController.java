package com.btca.bootcampalarm.controller;

import com.btca.bootcampalarm.dto.AuthenticationRequest;
import com.btca.bootcampalarm.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/mails")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("codes")
    public ResponseEntity<?> requestEmailCode(@RequestBody String email) {

        if(!mailService.duplicationCheck(email)) return ResponseEntity.badRequest().build();



        return ResponseEntity.ok().build();
    }

    @PostMapping("authorize")
    public ResponseEntity<?> authenticationEmailCode(@RequestBody AuthenticationRequest userRequest) {



        return ResponseEntity.ok().build();
    }

    @PostMapping("save")
    public ResponseEntity<?> saveForm(@RequestBody AuthenticationRequest userRequest) {



        return ResponseEntity.ok().build();
    }
}
