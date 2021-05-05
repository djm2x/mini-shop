package com.example.minishop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
// @EnableJpaRepositories("com.example.minishop.Repositories")
// @ComponentScan("com.example.minishop.Repositories")
public class MiniShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniShopApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
				.addMapping("/**").allowedOrigins("*").allowedMethods("*")
				// .allowedOrigins("http://localhost:4200")
				;
			}
		};
	}
}
