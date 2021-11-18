package com.hst.scrapper.modules.category.domain.exception

import com.hst.scrapper.global.exception.NotFoundException

/**
 * @author dlgusrb0808@gmail.com
 */
class CategoryNotFoundException(message: String = "Can not find category") : NotFoundException(message)