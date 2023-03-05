package com.btca.bootcampalarm.repository;

import com.btca.bootcampalarm.dto.SubscribeUserDto;

import java.util.List;

public interface SubscribeRepositoryCustom {
    List<SubscribeUserDto> findSubscribeAndUserByBootcampId(Long id);
}