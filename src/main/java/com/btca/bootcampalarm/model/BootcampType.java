package com.btca.bootcampalarm.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BootcampType {
    BOOTCAMP(0), DEV_FIELD(1);

    private final Integer value;

}