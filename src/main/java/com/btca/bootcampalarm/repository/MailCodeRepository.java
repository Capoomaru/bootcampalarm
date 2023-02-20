package com.btca.bootcampalarm.repository;

import com.btca.bootcampalarm.dto.MailCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MailCodeRepository extends JpaRepository<MailCode, Integer> {

    Optional<MailCode> findByEmail(String email);

}