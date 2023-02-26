package com.btca.bootcampalarm.dto;

import com.btca.bootcampalarm.model.BootcampType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BootcampDto {
    private Long id;
    private String name;
    private Integer type;

    public BootcampDto(Long id, String name, BootcampType type) {
        this.id = id;
        this.name = name;
        this.type = type.ordinal();
    }
}
