package com.hst.sitescrapper.interceptor;

import com.hst.sitescrapper.constants.GlobalConstants;
import com.hst.sitescrapper.exception.UnAuthorizedException;
import com.hst.sitescrapper.service.JwtTokenProvider;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationInterceptor.class);

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String authorizationHeader = request.getHeader(GlobalConstants.JWT.AUTHORIZATION_HEADER);

		if (StringUtils.isEmpty(authorizationHeader)) {
			throw new UnAuthorizedException("No authorization header presented");
		} else if (!authorizationHeader.matches("^Bearer .+$")) {
			throw new UnAuthorizedException(String.format("Invalid format authorization header. %s", authorizationHeader));
		}

		String token = authorizationHeader.split(" ")[1];
		if (!jwtTokenProvider.validateToken(token)) {
			throw new UnAuthorizedException("Invalid token");
		}

		return true;
	}
}
