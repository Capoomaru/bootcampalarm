package com.btca.bootcampalarm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;

@Entity
@Table(schema = "subscribe_bootcamp")
@Getter
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscribe extends BaseTimeEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bootcamp_id")
    private Bootcamp bootcampId;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private SubscribeStatus status;

    public Subscribe updateStatus(SubscribeStatus status) {
        this.status = status;

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscribe subscribe = (Subscribe) o;

        if (!Objects.equals(userId, subscribe.userId)) return false;
        if (!Objects.equals(bootcampId, subscribe.bootcampId)) return false;
        return status == subscribe.status;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (bootcampId != null ? bootcampId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
