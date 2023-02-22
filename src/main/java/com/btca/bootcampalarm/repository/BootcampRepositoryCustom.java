package com.btca.bootcampalarm.repository;

import com.btca.bootcampalarm.dto.BootcampDto;
import com.btca.bootcampalarm.model.BootcampType;

import java.util.List;

public interface BootcampRepositoryCustom {
    List<BootcampDto> findByType(BootcampType type);
}
