package com.hst.sitescrapper.configuration;

import com.hst.sitescrapper.constants.GlobalConstants;
import com.hst.sitescrapper.interceptor.JwtAuthenticationFilter;
import com.hst.sitescrapper.service.JwtTokenProvider;
import com.hst.sitescrapper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author dlgusrb0808@gmail.com
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserService userService;

	private static final String[] PUBLIC_API = {
			"/api/auth/signin",
			"/api/auth/signup",
			"/system/exception-entry"
	};

	private static final String ADMIN_API_PATTERN = "/api/admin/**";

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()		// REST API 용 인증을 위해 basic 설정 미사용 처리 (사용 시 폼 로그인 기반)
			.csrf().disable()			// REST API 기 때문에 csrf 보안 필요 X
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.authorizeRequests()
				.antMatchers(PUBLIC_API).permitAll()
				.antMatchers(ADMIN_API_PATTERN).hasRole(GlobalConstants.Roles.ADMIN)
				.anyRequest().hasRole(GlobalConstants.Roles.NORMAL_USER)
		.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, userService),
					UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
