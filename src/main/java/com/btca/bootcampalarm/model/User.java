package com.btca.bootcampalarm.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "user")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @Column(length = 30)
    private String mail;

    @Column(name = "subscribe_cnt")
    @ColumnDefault("0")
    private Integer cnt;

    @Builder.Default
    @OneToMany(mappedBy = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Subscribe> subscribeList = new ArrayList<>();
}
