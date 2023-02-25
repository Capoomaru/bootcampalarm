package com.btca.bootcampalarm.service;

import com.btca.bootcampalarm.model.User;
import com.btca.bootcampalarm.repository.UserRepository;
import com.btca.bootcampalarm.util.RandomCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class MailService {

    private final UserRepository userRepository;

    private final JavaMailSender javaMailSender;

    //TODO : 글 수정 필요
    public Integer sendCodeMail(String mailAddress) {
        int code = RandomCodeUtil.createCode();
        SimpleMailMessage msg = new SimpleMailMessage();

        StringBuilder subject = new StringBuilder();
        subject.append("[BootcampAlarm] 이메일 인증 코드");

        StringBuilder content = new StringBuilder();
        content.append("BootcampAlarm 인증요청\n");
        content.append("인증요청 코드 : ");
        content.append(code);

        msg.setTo(mailAddress);
        msg.setSubject(subject.toString());
        msg.setText(content.toString());
        javaMailSender.send(msg);

        return code;
    }

    @Transactional
    public void updateMailCode(String mail, Integer code) {
        User user = User.builder()
                .mail(mail)
                .code(code)
                .isValidate(false)
                .build();
        userRepository.save(user);
    }

    @Transactional
    public boolean validateCode(String mail, int code) {
        User user = userRepository.findByMail(mail).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        if (!user.getModifiedAt().isAfter(LocalDateTime.now().minusMinutes(5)))
            throw new RuntimeException("인증시간이 초과하였습니다");

        if (user.getCode() != code)
            throw new RuntimeException("인증번호가 일치하지않습니다");

        return true;
    }

    @Transactional(readOnly = true)
    public boolean isValidateUser(String mail) {
        User user = userRepository.findByMail(mail).orElseThrow(() -> new IllegalArgumentException("없는 회원이거나 인증 기록이 존재하지 않습니다."));
        return user.getModifiedAt().isAfter(LocalDateTime.now().minusMinutes(5));
    }

    public boolean mailFormatCheck(String mail) {
        return Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
                .matcher(mail)
                .matches();
    }

}
