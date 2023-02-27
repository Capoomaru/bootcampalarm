package com.btca.bootcampalarm.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(schema = "USER")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @NotNull
    @Column(length = 30)
    private String mail;

    @NotNull
    @Min(100000)
    @Max(999999)
    @Column
    private Integer code;

    @NotNull
    @Column(name = "is_validate", columnDefinition = "tinyint(1) default 0")
    private boolean isValidate;

    @Column(name = "subscribe_cnt")
    @ColumnDefault("0")
    @Builder.Default
    private Integer cnt = 0;

    @Builder.Default
    @OneToMany(mappedBy = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Subscribe> subscribeSet = new HashSet<>();

    public User updateIsValidate() {
        this.isValidate = true;
        return this;
    }

    public User updateSubscribeSet(Set<Subscribe> newSubscribeSet) {
        this.subscribeSet = newSubscribeSet;
        return this;
    }
}