package com.btca.bootcampalarm.service;

import com.btca.bootcampalarm.dto.BootcampDto;
import com.btca.bootcampalarm.model.BootcampType;
import com.btca.bootcampalarm.repository.BootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BootcampService {
    private final BootcampRepository bootCampRepository;

    @Transactional(readOnly = true)
    public List<List<BootcampDto>> getBootcampList() {
        List<List<BootcampDto>> bootcampList = new ArrayList<>();
        for (BootcampType type : BootcampType.values()) {
            List<BootcampDto> typeList = bootCampRepository.findByType(type);
            bootcampList.add(typeList);
        }
        return bootcampList;
    }
}
