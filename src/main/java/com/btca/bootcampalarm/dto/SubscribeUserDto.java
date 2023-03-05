package com.btca.bootcampalarm.dto;

import com.btca.bootcampalarm.model.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscribeUserDto {
    String userMail;
    Subscribe subscribe;
}
