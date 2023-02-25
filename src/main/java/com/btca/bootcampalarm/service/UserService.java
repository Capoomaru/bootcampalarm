package com.btca.bootcampalarm.service;

import com.btca.bootcampalarm.model.User;
import com.btca.bootcampalarm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public String register(String mail) {
        if(userRepository.existsByMail(mail))
            throw new RuntimeException("이미 등록된 계정입니다.");
        User user = User.builder()
                        .mail(mail)
                        .build();
        user = userRepository.save(user);

        return user.getMail();
    }

    @Transactional(readOnly = true)
    public boolean duplicationCheck(String mail) {
        return userRepository.existsByMail(mail);
    }

    public Integer updateSubscribe(String mail, List<String> checkList) {
        User user = userRepository.findByMail(mail).orElseThrow(() -> new RuntimeException("등록되지 않은 회원입니다."));
        user.getSubscribeList();

        return null;
    }

}
