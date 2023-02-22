package com.btca.bootcampalarm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "bootcamp_list")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bootcamp extends BaseTimeEntity {
    @Id
    @NotNull
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private BootcampType type;

}
