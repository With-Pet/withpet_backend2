package com.withpet.backend.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonDateEntity implements Serializable {
    @CreatedDate
    private LocalDateTime createdAt;    //등록일자
    @LastModifiedDate
    private LocalDateTime modifiedAt;   //업데이트 일자
}
