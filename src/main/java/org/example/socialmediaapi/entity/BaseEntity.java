package org.example.socialmediaapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "STATUS", nullable = false, columnDefinition = "INT DEFAULT 1")
    @NotNull
    public int status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATEDATE", updatable = false)
    @CreationTimestamp
    public Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATEDATE", insertable = false, updatable = false)
    @UpdateTimestamp
    public Date updateDate;

}
