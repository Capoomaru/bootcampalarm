package com.btca.bootcampalarm.service;

import com.btca.bootcampalarm.model.Bootcamp;
import com.btca.bootcampalarm.model.Subscribe;
import com.btca.bootcampalarm.model.SubscribeStatus;
import com.btca.bootcampalarm.model.User;
import com.btca.bootcampalarm.repository.BootcampRepository;
import com.btca.bootcampalarm.repository.SubscribeRepository;
import com.btca.bootcampalarm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BootcampRepository bootcampRepository;

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

    @Transactional
    public void updateSubscribe(String mail, List<Long> checkedBootcampIdList) {
        User user = userRepository.findByMail(mail).orElseThrow(() -> new RuntimeException("등록되지 않은 회원입니다."));

        Set<Subscribe> prevSubscribeSet = user.getSubscribeSet();

        Set<Subscribe> checkedSubscribeSet = new HashSet<>();

        for (Long bootcampId : checkedBootcampIdList) {
            Bootcamp bootcamp = bootcampRepository.findById(bootcampId).orElseThrow(() -> new RuntimeException("존재하지 않는 부트캠프입니다."));

            Subscribe subscribe = Subscribe.builder()
                    .userId(user)
                    .bootcampId(bootcamp)
                    .status(SubscribeStatus.SUBSCRIBE)
                    .build();
            checkedSubscribeSet.add(subscribe);
        }


        Set<Subscribe> addSubscribeSet = new HashSet<>(checkedSubscribeSet);
        Set<Subscribe> deleteSubscribeSet = new HashSet<>(prevSubscribeSet);

        addSubscribeSet.removeAll(prevSubscribeSet);
        deleteSubscribeSet.removeAll(checkedSubscribeSet);

        subscribeRepository.saveAll(addSubscribeSet);
        subscribeRepository.deleteAll(deleteSubscribeSet);
    }

    @Transactional(readOnly = true)
    public List<Long> getSubscribeList(String mail, Integer code) {
        User user = userRepository.findByMail(mail).orElseThrow(() -> new RuntimeException("등록되지 않은 회원입니다."));

        if (!user.getModifiedAt().isAfter(LocalDateTime.now().minusMinutes(2)))
            throw new RuntimeException("인증시간이 초과하였습니다");

        if (!Objects.equals(user.getCode(), code))
            throw new RuntimeException("인증번호가 일치하지않습니다");

        List<Long> subscribeBootcampList = new ArrayList<>();

        for (Subscribe subscribe : user.getSubscribeSet()) {
            subscribeBootcampList.add(subscribe.getBootcampId().getId());
        }

        subscribeBootcampList.sort(Comparator.naturalOrder());

        return subscribeBootcampList;
    }

}
