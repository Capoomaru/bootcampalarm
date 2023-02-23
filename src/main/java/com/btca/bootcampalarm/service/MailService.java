package com.btca.bootcampalarm.service;

import com.btca.bootcampalarm.model.MailCode;
import com.btca.bootcampalarm.repository.MailCodeRepository;
import com.btca.bootcampalarm.util.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class MailService {

    private final MailCodeRepository mailCodeRepository;

    private final JavaMailSender javaMailSender;

    //TODO : 글 수정 필요
    @Transactional
    public void sendMail(String username) {
        int code = RandomCodeUtil.createCode();
        SimpleMailMessage msg = new SimpleMailMessage();

        StringBuilder subject = new StringBuilder();
        subject.append("[BootcampAlarm] 이메일 인증 코드");

        StringBuilder content = new StringBuilder();
        content.append("BootcampAlarm 인증요청\n");
        content.append(username);
        content.append("님의 인증요청 코드\n");
        content.append(code);

        msg.setTo(username);
        msg.setSubject(subject.toString());
        msg.setText(content.toString());
        javaMailSender.send(msg);

        MailCode mailCode = MailCode.builder()
                .mail(username)
                .code(code)
                .isValidate(false)
                .build();
        mailCodeRepository.save(mailCode);
    }

    @Transactional
    public boolean validateCode(String email, int code) {
        MailCode emailCode = mailCodeRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("인증 기록이 존재하지 않습니다."));

        if(emailCode.getModifiedAt().isAfter(LocalDateTime.now().minusMinutes(5)) && emailCode.getCode() == code) {
            mailCodeRepository.save(emailCode.updateIsValidate());
            return true;
        }
        else
            return false;

    }

    @Transactional(readOnly = true)
    public boolean isValidateUser(String email) {
        MailCode mailCode = mailCodeRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("없는 회원이거나 인증 기록이 존재하지 않습니다."));
        return mailCode.getModifiedAt().isAfter(LocalDateTime.now().minusMinutes(5));
    }

    @Transactional(readOnly = true)
    public boolean duplicationCheck(String email) {
        return mailCodeRepository.existsByEmail(email);
    }

}
