package com.hst.scrapper.scrap.domain.exception

import com.hst.scrapper.global.exception.BadRequestException

/**
 * @author dlgusrb0808@gmail.com
 */
class InvalidScrapUrlException(message: String = "Scrap url is not matched.") : BadRequestException(message)