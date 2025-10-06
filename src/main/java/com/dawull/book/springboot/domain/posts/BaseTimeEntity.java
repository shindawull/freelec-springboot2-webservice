package com.dawull.book.springboot.domain.posts;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
/* @MappedSuperclass는 jpaEntity클래스들이 상속할 경우 컬럼으로 인식하도록
* @EntityListeners(AuditingEntityListener.class)
* auditing 감시하다 뜻아닌 자동으로 값을 넣어주는 기능
* */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createDate;

    @CreatedDate
    private LocalDateTime modifiedDate;
}
