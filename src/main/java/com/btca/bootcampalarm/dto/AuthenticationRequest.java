package com.btca.bootcampalarm.dto;


import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private Integer code;
}
