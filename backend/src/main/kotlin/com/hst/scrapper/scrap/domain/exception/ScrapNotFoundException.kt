package com.hst.scrapper.scrap.domain.exception

import com.hst.scrapper.global.exception.NotFoundException

/**
 * @author dlgusrb0808@gmail.com
 */
class ScrapNotFoundException(message: String = "Can not find scrap.") : NotFoundException(message)