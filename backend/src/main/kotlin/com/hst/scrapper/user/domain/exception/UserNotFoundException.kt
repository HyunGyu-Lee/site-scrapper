package com.hst.scrapper.user.domain.exception

import com.hst.scrapper.global.exception.NotFoundException

/**
 * @author dlgusrb0808@gmail.com
 */
class UserNotFoundException(message: String = "Can not find user") : NotFoundException(message)