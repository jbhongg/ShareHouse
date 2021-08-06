package com.ssafy.sharehouse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.util.CommonInterceptor;

@Configuration
public class InterceptorEncodeFilterConfig implements WebMvcConfigurer {

	@Autowired
	CommonInterceptor commonInterceptor;

//	@Autowired
//	LoginInterceptor logininterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
		interceptorRegistry.addInterceptor(commonInterceptor).addPathPatterns("/**");
		//interceptorRegistry.addInterceptor(logininterceptor);
	}
	@Bean
	public CharacterEncodingFilter getCharacterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;

	}
}
