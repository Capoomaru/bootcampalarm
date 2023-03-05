package com.btca.bootcampalarm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BootcampUpdateDto {
    private Long id;
    @JsonProperty("bootcamp_name")
    private String bootcampName;
    private String company;
    private String generation;
    private String title;
    @JsonProperty("tech_stack")
    private List<String> techStack;
    private List<String> field;
    @JsonProperty("reg_start_date")
    private Date regStartDate;
    @JsonProperty("reg_end_date")
    private Date regEndDate;
    @JsonProperty("camp_start_date")
    private Date campStartDate;
    @JsonProperty("camp_end_date")
    private Date campEndDate;
    @JsonProperty("apply_link")
    private String applyLink;
}
