package com.hst.scrapper.global.config

import com.hst.scrapper.global.filter.JwtAuthenticationFilter
import com.hst.scrapper.user.application.UserService
import com.hst.scrapper.user.domain.repo.AuthTokenRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
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

    @Autowired
    lateinit var authTokenRepository: AuthTokenRepository

    @Autowired
    lateinit var userService: UserService

    companion object {
        private val PUBLIC_APIS: Array<String> = arrayOf(
            "/api/users/signin", "/api/users/signup", "/system/exception-entry"
        )
    }

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .mvcMatchers(*PUBLIC_APIS)
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
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
            .anyRequest().hasRole("USER")
            .and()
            .addFilterBefore(
                JwtAuthenticationFilter(authTokenRepository, userService),
                UsernamePasswordAuthenticationFilter::class.java
            )
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

}