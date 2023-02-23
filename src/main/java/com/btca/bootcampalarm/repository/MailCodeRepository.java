package com.btca.bootcampalarm.repository;

import com.btca.bootcampalarm.model.MailCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailCodeRepository extends JpaRepository<MailCode, Integer> {

    Optional<MailCode> findByMail(String mail);
    Boolean existsByMail(String mail);

}