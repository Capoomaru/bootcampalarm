package com.btca.bootcampalarm.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MailCodeRepositoryImpl implements MailCodeRepositoryCustom {
    //private final

    @Override
    public Boolean findValidatedByEMail(String email) {

        return null;
    }


}
