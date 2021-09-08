package com.hst.scrapper.global.entity

import javax.persistence.*

/**
 * @author dlgusrb0808@gmail.com
 */
@MappedSuperclass
open class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

}