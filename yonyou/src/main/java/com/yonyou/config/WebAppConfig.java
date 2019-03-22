/**********************************************************************
* $Id: Application.java Application ,v0.1 2016年7月12日 下午4:39:40 DuanMinglei Exp $
* Copyright ©2016 yonyou . All rights reserved
***********************************************************************/

package com.yonyou.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.github.pagehelper.PageHelper;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 功能说明：应用配置 
 * 创建者：段明磊 
 * E-mail: duanml1@yonyou.com 
 * 创建时间：2016年7月13日
 * 
 * <pre>
* 修改时间:       修改者:            
* 修改内容：
 * </pre>
 * 
 * 版本：0.1
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		pageHelper.setProperties(p);
		return pageHelper;
	}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	@Bean
	public WebInterceptor webInterceptor() {
		return new WebInterceptor();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		// registry.addResourceHandler("/*.html").addResourceLocations("classpath:/html5/");
	}

	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(webInterceptor()).addPathPatterns("/**");
	}

	/* 配置 swagger开始 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.select()
	            .apis(RequestHandlerSelectors.basePackage("com.yonyou.controller"))
	           // .apis(RequestHandlerSelectors.basePackage("com.yonyou.cc.app.controller"))
	            .paths(PathSelectors.any())
	            .build();
	            //.globalOperationParameters(setHeaderToken());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("项目接口文档")
				// .contact(new Contact("rongrong", "", "2250454190@qq.com"))
				// .description("这是SWAGGER_2生成的接口文档")
				// .termsOfServiceUrl("NO terms of service")
				// .license("The Apache License, Version 2.0")
				// .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.version("v1.0").build();
	}

}
