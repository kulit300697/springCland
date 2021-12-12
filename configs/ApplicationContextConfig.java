package edu.vinaenter.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration // Đây là cơ chế DI IOC
@ComponentScans({ @ComponentScan(value = "edu.vinaenter.*") })
public class ApplicationContextConfig {
	@Bean("viewResolver") // tương ứng như 1 object
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver views = new InternalResourceViewResolver();
		views.setPrefix("/WEB-INF/views/"); // định nghĩa đầu
		views.setSuffix(".jsp"); // Phần đuôi
		views.setOrder(1);
		return views;
	}

	@Bean(name = "viewResolverTiles")
	public ViewResolver getViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		viewResolver.setOrder(0); //Ưu tiên sử dụng Tiles trước
		return viewResolver;
	}

	@Bean(name = "tilesConfigurer")
	public TilesConfigurer getTilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("/WEB-INF/templates/tiles-admin.xml", "/WEB-INF/templates/tiles-public.xml",
				"/WEB-INF/templates/tiles-auth.xml");
		return tilesConfigurer;
	}
	
	@Bean(name = "multipartResolver")
	public MultipartResolver getMultipartResolver() {
		CommonsMultipartResolver resover = new CommonsMultipartResolver();
//		resover.setMaxUploadSize(1 * 1024 * 1024);
		return resover;
	}
}
