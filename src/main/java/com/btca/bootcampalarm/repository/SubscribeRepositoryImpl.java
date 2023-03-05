package com.btca.bootcampalarm.repository;

import com.btca.bootcampalarm.dto.SubscribeUserDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.btca.bootcampalarm.model.QSubscribe.subscribe;

@Repository
@RequiredArgsConstructor
public class SubscribeRepositoryImpl implements SubscribeRepositoryCustom {
private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<SubscribeUserDto> findSubscribeAndUserByBootcampId(Long bootcampId) {
        return jpaQueryFactory.select(Projections.constructor(SubscribeUserDto.class,
                        subscribe.userId.mail, subscribe))
                .from(subscribe)
                .where(subscribe.bootcampId.id.eq(bootcampId))
                .fetch();
    }
}
