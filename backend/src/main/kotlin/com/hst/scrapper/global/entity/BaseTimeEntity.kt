package com.hst.scrapper.global.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

/**
 * @author dlgusrb0808@gmail.com
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseTimeEntity: BaseEntity() {

    @CreatedDate
    @Column
    var createdAt: LocalDateTime = LocalDateTime.MIN
        private set

    @LastModifiedDate
    @Column(name = "MO")
    var modifiedAt: LocalDateTime = LocalDateTime.MIN
        private set
}