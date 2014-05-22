package org.yogocodes.diskmonitor.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@EnableWebMvc
@EnableScheduling
@Component
@ComponentScan("org.yogocodes.diskmonitor")
public class DiskMonitorConfig {

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Bean(destroyMethod = "close")
	public DataSource setupDataSource() {
		final BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/diskmonitor");
		dataSource.setUsername("diskuser");
		dataSource.setPassword("diskmon");
		dataSource.setMaxActive(50);
		dataSource.setInitialSize(15);
		return dataSource;
	}

	@Bean
	public JdbcTemplate createJdbcTemplate() {
		return new JdbcTemplate(setupDataSource());
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations(
				"classpath:/assets/").addResourceLocations("/css/**").addResourceLocations("/css/").addResourceLocations("/img/**").addResourceLocations("/img/").addResourceLocations("/js/**").addResourceLocations("/js/");
	}

}
