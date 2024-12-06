package org.example.socialmediaapi.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.socialmediaapi.constants.Status;
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
@JsonFilter("statusFilter")
public abstract class BaseEntity {

    @Column(name = "STATUS", nullable = false, columnDefinition = "INT DEFAULT 1")
    @NotNull
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public int status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATEDATE", updatable = false)
    @CreationTimestamp
    public Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATEDATE", insertable = false)
    @UpdateTimestamp
    public Date updateDate;

    @JsonGetter("status")
    public int getStatus() {
        return status == 1 ? Status.ACTIVE.getValue() : Status.INACTIVE.getValue();
    }

}
