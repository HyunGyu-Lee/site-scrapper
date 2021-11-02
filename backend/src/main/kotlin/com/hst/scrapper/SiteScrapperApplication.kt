package com.hst.scrapper

import com.hst.scrapper.modules.user.infrastructure.jwt.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableWebSecurity
@EnableJpaAuditing
@EnableConfigurationProperties(JwtProperties::class)
class SiteScrapperApplication

fun main(args: Array<String>) {
    runApplication<SiteScrapperApplication>(*args)
}
