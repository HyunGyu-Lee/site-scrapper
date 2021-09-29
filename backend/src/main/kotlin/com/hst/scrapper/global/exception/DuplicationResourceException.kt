package com.hst.scrapper.global.exception

import org.springframework.http.HttpStatus

/**
 * Copyright 2021 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author hyungyu.lee@nhn.com
 * @date 2021-09-30
 */
open class DuplicationResourceException(message: String = "") : BaseException(HttpStatus.CONFLICT, message)