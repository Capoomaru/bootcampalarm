package com.btca.bootcampalarm.repository;

public interface UserRepositoryCustom {
    Boolean findIsValidateByMail(String mail);
    Integer getCodeByMail(String mail);

}
