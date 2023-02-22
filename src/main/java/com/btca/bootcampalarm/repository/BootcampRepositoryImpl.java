package com.btca.bootcampalarm.repository;

import com.btca.bootcampalarm.dto.BootcampDto;
import com.btca.bootcampalarm.model.BootcampType;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.btca.bootcampalarm.model.QBootcamp.bootcamp;

@RequiredArgsConstructor
@Repository
public class BootcampRepositoryImpl implements BootcampRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BootcampDto> findByType(BootcampType type) {
        return jpaQueryFactory.select(Projections.constructor(BootcampDto.class,
                        bootcamp.name,
                        bootcamp.type))
                .from(bootcamp)
                .where(bootcamp.type.eq(type))
                .fetch();
    }
}
