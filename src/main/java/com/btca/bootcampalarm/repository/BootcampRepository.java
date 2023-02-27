package com.btca.bootcampalarm.repository;

import com.btca.bootcampalarm.dto.BootcampDto;
import com.btca.bootcampalarm.model.Bootcamp;
import com.btca.bootcampalarm.model.BootcampType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface BootcampRepository extends JpaRepository<Bootcamp, Long>, BootcampRepositoryCustom {
    ArrayList<BootcampDto> findByTypeEqualsOrderByIdDesc(BootcampType type);
    Optional<Bootcamp> findById(Long id);
}
