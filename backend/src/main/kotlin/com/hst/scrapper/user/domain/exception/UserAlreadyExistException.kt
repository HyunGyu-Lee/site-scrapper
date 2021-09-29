package com.hst.scrapper.user.domain.exception

import com.hst.scrapper.global.exception.DuplicationResourceException

/**
 * Copyright 2021 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author hyungyu.lee@nhn.com
 * @date 2021-09-30
 */
class UserAlreadyExistException(message: String = "Already exist user") : DuplicationResourceException(message)