package com.btca.bootcampalarm.repository;

public interface MailCodeRepositoryCustom {
    Boolean findValidatedByEMail(String email);
}
