package com.btca.bootcampalarm.service;

import com.btca.bootcampalarm.dto.BootcampDto;
import com.btca.bootcampalarm.model.BootcampType;
import com.btca.bootcampalarm.repository.BootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BootcampService {
    private final BootcampRepository repository;

    public List<List<BootcampDto>> getBootcampList() {
        List<List<BootcampDto>> bootcampList = new ArrayList<>();
        for (BootcampType type : BootcampType.values()) {
            List<BootcampDto> typeList = repository.findByType(type);
            bootcampList.add(typeList);
        }
        return bootcampList;
    }
}
