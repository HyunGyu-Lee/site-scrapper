package com.hst.sitescrapper.configuration;

import com.hst.sitescrapper.interceptor.JwtAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dlgusrb0808@gmail.com
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Autowired
	private JwtAuthenticationInterceptor jwtAuthenticationInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtAuthenticationInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/api/auth/**", "/public/**");
	}
}
