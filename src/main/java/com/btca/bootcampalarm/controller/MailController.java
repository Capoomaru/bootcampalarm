package com.btca.bootcampalarm.controller;

import com.btca.bootcampalarm.service.MailService;
import com.btca.bootcampalarm.util.MailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mails")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("codes")
    public ResponseEntity<?> requestEmailCode(@RequestBody String email) {



        return ResponseEntity.ok().build();
    }
}
