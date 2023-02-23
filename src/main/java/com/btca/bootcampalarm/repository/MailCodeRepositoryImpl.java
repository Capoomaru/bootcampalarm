package com.btca.bootcampalarm.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.btca.bootcampalarm.model.QMailCode.mailCode;

@Repository
@RequiredArgsConstructor
public class MailCodeRepositoryImpl implements MailCodeRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Boolean findIsValidateByMail(String mail) {
        return jpaQueryFactory.select(mailCode.isValidate)
                .from(mailCode)
                .where(mailCode.mail.eq(mail))
                .fetchFirst();
    }

}
