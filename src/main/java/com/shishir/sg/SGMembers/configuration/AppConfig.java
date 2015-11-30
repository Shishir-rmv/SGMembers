package com.shishir.sg.SGMembers.configuration;

import java.util.List;

import org.springframework.cglib.transform.impl.AddDelegateTransformer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.shishir.sg")
public class AppConfig extends WebMvcConfigurerAdapter {

//	/**
//	 * Configure ViewResolvers to deliver preferred views.
//	 */
//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/views/");
//		viewResolver.setSuffix(".jsp");
//		registry.viewResolver(viewResolver);
//	}

	/**
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript
	 * etc...
	 */
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/static/**").addResourceLocations(
//				"/static/");
//	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(
				"/static/html/");
	}


	// /**
	// * Configure Converter to be used.
	// * In our example, we need a converter to convert string values[Roles] to
	// UserProfiles in newUser.jsp
	// */
	// @Override
	// public void addFormatters(FormatterRegistry registry) {
	// registry.addConverter(roleToUserProfileConverter);
	// }

	/**
	 * Configure MessageSource to lookup any validation/error message in
	 * internationalized property files
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	/**
	 * Optional. It's only required when handling '.' in @PathVariables which
	 * otherwise ignore everything after last '.' in
	 * 
	 * @PathVaidables argument. It's a known bug in Spring
	 *                [https://jira.spring.io/browse/SPR-6164], still present in
	 *                Spring 4.1.7. This is a workaround for this issue.
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("utf-8");
		commonsMultipartResolver.setMaxUploadSize(50000000);
		return commonsMultipartResolver;
	}
	
	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(converter());
	}
	
	@Bean
    MappingJackson2HttpMessageConverter converter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        return converter;
    }
	
}