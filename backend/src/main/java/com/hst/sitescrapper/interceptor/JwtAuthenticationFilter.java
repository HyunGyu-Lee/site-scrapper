package com.hst.sitescrapper.interceptor;

import com.hst.sitescrapper.service.JwtTokenProvider;
import com.hst.sitescrapper.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author dlgusrb0808@gmail.com
 */
public class JwtAuthenticationFilter extends GenericFilterBean {

	private final JwtTokenProvider jwtTokenProvider;
	private final UserService userService;

	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserService userService) {
		this.jwtTokenProvider = jwtTokenProvider;
		this.userService = userService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
		if (jwtTokenProvider.validateToken(token)) {
			Authentication authentication = userService.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}
}
