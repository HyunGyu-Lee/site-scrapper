package com.hst.scrapper.user.infrastructure.jpa

import com.hst.scrapper.user.domain.entity.User
import com.hst.scrapper.user.domain.repo.UserRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author dlgusrb0808@gmail.com
 */
@Repository
interface JpaUserRepository: UserRepository, JpaRepository<User, Long>