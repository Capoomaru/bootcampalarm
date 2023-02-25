package com.btca.bootcampalarm.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.btca.bootcampalarm.model.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Boolean findIsValidateByMail(String mail) {
        return jpaQueryFactory.select(user.isValidate)
                .from(user)
                .where(user.mail.eq(mail))
                .fetchFirst();
    }

    @Override
    public Integer getCodeByMail(String mail) {
        return jpaQueryFactory.select(user.code)
                .from(user)
                .where(user.mail.eq(mail))
                .fetchFirst();
    }


}
