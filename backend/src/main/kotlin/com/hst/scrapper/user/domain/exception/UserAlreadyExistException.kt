package com.hst.scrapper.user.domain.exception

import com.hst.scrapper.global.exception.DuplicationResourceException

/**
 * @author dlgusrb0808@gmail.com
 */
class UserAlreadyExistException(message: String = "Already exist user") : DuplicationResourceException(message)