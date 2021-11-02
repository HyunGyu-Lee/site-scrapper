package com.hst.scrapper.modules.user.infrastructure.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

/**
 * @author dlgusrb0808@gmail.com
 */
@ConstructorBinding
@ConfigurationProperties("spring.jwt")
data class JwtProperties(val expireDay: Long, val secretKey: String)

