package com.hst.scrapper.user.infrastructure.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

/**
 * Copyright 2021 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author hyungyu.lee@nhn.com
 * @date 2021-09-03
 */
@ConstructorBinding
@ConfigurationProperties("spring.jwt")
data class JwtProperties(val expireDay: Long, val secretKey: String)

