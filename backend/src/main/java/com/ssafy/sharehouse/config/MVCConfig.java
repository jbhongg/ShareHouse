package com.ssafy.sharehouse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.ssafy.interceptor.ConfirmInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan("com.ssafy")
public class MVCConfig implements WebMvcConfigurer {
	/*
	 * <bean
	 * class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	 * <property name="prefix" value="/"/> <property name="suffix" value=".jsp"/>
	 * </bean>
 */	
	
	@Autowired
	private ConfirmInterceptor comfiemInterceptor;
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/* <mvc:resources location="/resources/" mapping="/**"/> */
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(comfiemInterceptor).addPathPatterns("/article/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/// img/** 로 들어오는 요청은 /WEB-INF/images 에 매핑시키며 1년간 caching
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/");
		registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
	}
	
	/*
	<beans:bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
    <beans:property name="order" value="0"/>
    </beans:bean>

    <beans:bean id="download" class="edu.ssafy.util.FileDownload"/>
	*/
	@Bean
	public BeanNameViewResolver beanNameViewResolver() {
		BeanNameViewResolver rnvr = new BeanNameViewResolver();
		rnvr.setOrder(0);
		return rnvr;
	}
//	@Bean
//	public FileDownload download() {
//		return new FileDownload();
//	}
}
