package com.example.minishop.Configs;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		// .configurationSource(request -> {

		// 	// new CorsConfiguration().applyPermitDefaultValues()
		// 	CorsConfiguration configuration = new CorsConfiguration();
		// 	configuration.setAllowedOrigins(Arrays.asList("*"));
		// 	configuration.setAllowedMethods(Arrays.asList("*"));
		// 	// UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// 	// source.registerCorsConfiguration("/**", configuration);
		// 	return configuration;
			
		// })
		.and()
		.csrf().disable()
		// .authorizeRequests()                                                                
		// .antMatchers("/**").permitAll()
		;
	}
}