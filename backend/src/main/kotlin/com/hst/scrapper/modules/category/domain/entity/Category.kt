package com.hst.scrapper.modules.category.domain.entity

import com.hst.scrapper.modules.user.domain.entity.User
import javax.persistence.*

/**
 * @author dlgusrb0808@gmail.com
 */
@Entity
@Table
class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column
    var name: String = ""
        private set

    @Column
    var order: Int = 1

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    var user: User? = null

    @ManyToOne
    var parentCategory: Category? = null
        private set

    @OneToMany(mappedBy = "parentCategory", cascade = [CascadeType.ALL], orphanRemoval = true)
    var childCategories: Set<Category> = HashSet()
        private set
}