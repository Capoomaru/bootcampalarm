package com.btca.bootcampalarm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailRequestDto {
    private String mail;
    @JsonProperty("is_new")
    private Boolean isNew;
}
