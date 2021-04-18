package com.example.minishop;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MiniShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniShopApplication.class, args);
	}

	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080");
			}
		};
	}

	@Bean
	public DataSource dataSource() {
	   BasicDataSource ans = new BasicDataSource();
	//    ans.setDriverClassName("org.sqlite.JDBC");
	   ans.setUrl("jdbc:sqlite:dev.db");
	//    ans.set
	   ans.setMaxActive(1);
 
 
	   return ans;
	}

}
