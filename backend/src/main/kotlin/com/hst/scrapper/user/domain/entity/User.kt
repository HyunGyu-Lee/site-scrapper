package com.hst.scrapper.user.domain.entity

import com.hst.scrapper.global.entity.BaseTimeEntity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

/**
 * @author dlgusrb0808@gmail.com
 */
@Entity
@Table
class User : BaseTimeEntity(), UserDetails {
    @Column
    var email: String = ""
        private set

    @Column
    var loginId: String = ""
        private set

    @Column
    var loginPassword: String = ""
        private set

    @Column
    var name: String = ""
        private set

    @Column
    var profileImageUrl: String = ""
        private set

    @Enumerated
    @Convert(converter = UserStatusType.Converter::class)
    var userStatusType: UserStatusType? = null
        private set

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")])
    var roles: Set<String> = setOf()

    override fun getAuthorities() = roles.map { SimpleGrantedAuthority(it) }.toMutableSet()

    override fun getPassword() = loginPassword

    override fun getUsername() = loginId

    override fun isAccountNonExpired() = false

    override fun isAccountNonLocked() = false

    override fun isCredentialsNonExpired() = false

    override fun isEnabled() = true

    companion object {
        fun createUser(loginId: String, loginPassword: String, email: String, name: String): User {
            val user = User()
            user.loginId = loginId
            user.loginPassword = loginPassword
            user.email = email
            user.name = name
            return user
        }
    }
}