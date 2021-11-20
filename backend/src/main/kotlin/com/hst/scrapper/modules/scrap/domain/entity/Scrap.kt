package com.hst.scrapper.modules.scrap.domain.entity

import com.hst.scrapper.global.entity.BaseTimeEntity
import com.hst.scrapper.modules.category.domain.entity.Category
import com.hst.scrapper.modules.user.domain.entity.User
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "scraped_user_id")
    var scrapedUser: User? = null
        private set

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    var category: Category? = null
        private set

    companion object {
        fun createScrap(
            url: String,
            title: String,
            description: String,
            image: String,
            scrapedUser: User?,
            category: Category
        ): Scrap {
            val scrap = Scrap()
            scrap.url = url
            scrap.title = title
            scrap.description = description
            scrap.image = image
            scrap.scrapedUser = scrapedUser
            scrap.category = category
            return scrap
        }
    }
}