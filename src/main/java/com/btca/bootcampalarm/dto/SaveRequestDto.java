package com.btca.bootcampalarm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveRequestDto {
    private String mail;
    private Integer code;

    @JsonProperty("subscribe_list")
    private List<Long> subscribeList;
}
