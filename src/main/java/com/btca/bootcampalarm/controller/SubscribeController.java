package com.btca.bootcampalarm.controller;

import com.btca.bootcampalarm.dto.SaveRequestDto;
import com.btca.bootcampalarm.service.UserService;
import com.btca.bootcampalarm.util.MailUtils;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscribes")
@RequiredArgsConstructor
public class SubscribeController {
    private final MailUtils mailUtils;
    private final UserService userService;

    @PostMapping("forms")
    public ResponseEntity<?> saveForm(@RequestBody SaveRequestDto saveRequestDto) {
        if(!mailUtils.mailFormatCheck(saveRequestDto.getMail()))
            throw new IllegalArgumentException("이메일 형식이 아닙니다");

        userService.updateSubscribe(saveRequestDto.getMail(), saveRequestDto.getCode(),
                saveRequestDto.getSubscribeList());

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Long>> getSubscribeList(
            @Parameter(name="mail", description = "abc@test.com") @RequestParam String mail,
            @Parameter(name="code",description = "123456") @RequestParam Integer code) {
        if(!mailUtils.mailFormatCheck(mail))
            throw new IllegalArgumentException("이메일 형식이 아닙니다");

        List<Long> list = userService.getSubscribeList(mail, code);


        return ResponseEntity.ok(list);
    }
}
