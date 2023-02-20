package com.btca.bootcampalarm.service;

import com.btca.bootcampalarm.dto.MailCode;
import com.btca.bootcampalarm.repository.MailCodeRepository;
import com.btca.bootcampalarm.util.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MailService {

    private final MailCodeRepository mailCodeRepository;

    //@Autowired
    //private JavaMailSender javaMailSender;

    //TODO : 글 수정 필요
    @Transactional
    public void sendMail(String username, String nickname) {
        int code = RandomCodeUtil.createCode();
        SimpleMailMessage msg = new SimpleMailMessage();

        StringBuilder subject = new StringBuilder();
        subject.append("[ALGo] 이메일 인증 코드");

        StringBuilder content = new StringBuilder();
        content.append("ALGo 인증요청\n");
        content.append(nickname);
        content.append("님의 인증요청 코드\n");
        content.append(code);

        msg.setTo(username);
        msg.setSubject(subject.toString());
        msg.setText(content.toString());
        //javaMailSender.send(msg);

        MailCode mailCode = MailCode.builder()
                .email(username)
                .code(code)
                .isValidate(false)
                .build();
        mailCodeRepository.save(mailCode);
    }

    @Transactional
    public boolean validateCode(String email, int code) {
        MailCode emailCode = mailCodeRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("인증 기록이 존재하지 않습니다."));
        /*
        if(emailCode.getModifiedAt().isAfter(LocalDateTime.now().minusMinutes(5)) && emailCode.getCode() == code) {
            mailCodeRepository.save(emailCode.updateIsValidate());
            return true;
        }
        else
            return false;
        */
        return true;
    }

    @Transactional(readOnly = true)
    public boolean isValidateUser(String email) {
        MailCode mailCode = mailCodeRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("없는 회원이거나 인증 기록이 존재하지 않습니다."));
        //return eMailCode.getModifiedAt().isAfter(LocalDateTime.now().minusMinutes(5));
        return true;
    }
}
