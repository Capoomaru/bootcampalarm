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
    private String name;
    private Integer type;

    public BootcampDto(String name, BootcampType type) {
        this.name = name;
        this.type = type.ordinal();
    }
}
