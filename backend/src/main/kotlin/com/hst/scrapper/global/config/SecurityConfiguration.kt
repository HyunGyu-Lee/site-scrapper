package com.hst.scrapper.global.config

import com.hst.scrapper.global.filter.JwtAuthenticationFilter
import com.hst.scrapper.user.application.UserService
import com.hst.scrapper.user.domain.repo.AuthTokenRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * @author dlgusrb0808@gmail.com
 */
@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    companion object {
        private val PUBLIC_APIS: Array<String> = arrayOf(
            "/api/users/signin", "/api/users/signup", "/system/exception-entry"
        )
    }

    override fun configure(http: HttpSecurity) {
        http.httpBasic()
            .disable()
            .csrf()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(*PUBLIC_APIS).permitAll()
            .anyRequest().hasRole("USER")
            .and()
            .addFilterBefore(jwtAuthenticationFilter(null, null), UsernamePasswordAuthenticationFilter::class.java)

    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun jwtAuthenticationFilter(
        authTokenRepository: AuthTokenRepository?, userService: UserService?
    ): JwtAuthenticationFilter {
        return JwtAuthenticationFilter(authTokenRepository!!, userService!!)
    }

}