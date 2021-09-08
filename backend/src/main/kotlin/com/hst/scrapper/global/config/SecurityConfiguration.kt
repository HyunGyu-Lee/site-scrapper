package com.hst.scrapper.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * @author dlgusrb0808@gmail.com
 */
@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    companion object {
        private val PUBLIC_APIS: Array<String> = arrayOf(
            "/api/auth/signin", "/api/auth/signup", "/system/exception-entry"
        )
    }

    override fun configure(http: HttpSecurity) {
        http
            .httpBasic()
                .disable()
            .csrf()
                .disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .authorizeRequests()
                .antMatchers(*PUBLIC_APIS).permitAll()
                .anyRequest().hasRole("USER")

    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

}