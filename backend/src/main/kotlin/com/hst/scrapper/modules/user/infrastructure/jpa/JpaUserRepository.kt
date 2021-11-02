package com.hst.scrapper.modules.user.infrastructure.jpa

import com.hst.scrapper.modules.user.domain.entity.User
import com.hst.scrapper.modules.user.domain.repo.UserRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author dlgusrb0808@gmail.com
 */
@Repository
interface JpaUserRepository: UserRepository, JpaRepository<User, Long>