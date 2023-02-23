package com.btca.bootcampalarm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(schema = "EMAIL_CODE")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailCode extends BaseTimeEntity {
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

    public MailCode updateIsValidate() {
        this.isValidate = true;
        return this;
    }

}