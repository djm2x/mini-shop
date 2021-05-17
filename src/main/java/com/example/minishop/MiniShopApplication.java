package com.example.minishop;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
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

	// @Bean
	// public DataSource dataSource() {
	//    BasicDataSource ans = new BasicDataSource();
	// //    ans.setDriverClassName("org.sqlite.JDBC");
	//    ans.setUrl("jdbc:sqlite:dev-mini-shop.db");
	// //    ans.set
	// //    ans.setMaxActive(1);
	// //    ans.setMaxIdle(5);
 
 
	//    return ans;
	// }

	
}
