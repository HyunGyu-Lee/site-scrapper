package com.hst.scrapper.scrap.domain.entity

import com.hst.scrapper.global.entity.BaseTimeEntity
import com.hst.scrapper.user.domain.entity.User
import javax.persistence.*

/**
 * @author dlgusrb0808@gmail.com
 */
@Entity
@Table
class Scrap : BaseTimeEntity() {
    @Column
    var url: String = ""
        private set

    @Column
    var title: String = ""
        private set

    @Column
    var description: String = ""
        private set

    @Column
    var image: String = ""
        private set

    @ManyToOne
    @JoinColumn(name = "scraped_user_id")
    var scrapedUser: User? = null
}