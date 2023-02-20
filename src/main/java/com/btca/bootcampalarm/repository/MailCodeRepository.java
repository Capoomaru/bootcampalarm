package com.btca.bootcampalarm.repository;

import com.btca.bootcampalarm.model.MailCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailCodeRepository extends JpaRepository<MailCode, Integer> {

    Optional<MailCode> findByEmail(String email);
    Boolean existsByEmail(String email);

}