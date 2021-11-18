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
        private set

    @Column
    var name: String = ""
        private set

    @Column
    var order: Int = 1
        private set

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    var user: User? = null

    @ManyToOne
    var parentCategory: Category? = null
        private set

    @OneToMany(mappedBy = "parentCategory", cascade = [CascadeType.ALL], orphanRemoval = true)
    @OrderBy("order")
    var childCategories: Set<Category> = mutableSetOf()
        private set

    fun changeName(name: String) {
        this.name = name
    }

    fun changeOrder(order: Int) {
        this.order = order
    }

    fun addChild(child: Category) {
        this.childCategories.plus(child)
        child.parentCategory = this
        child.order = this.childCategories.size + 1
    }
}