package com.example.minishop.configuration.security;

import java.util.Arrays;

import com.example.minishop.repositories.UsersRepository;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger;
    private final UsersRepository userRepo;
    private final JwtTokenFilter jwtTokenFilter;

    @Value("${springdoc.api-docs.path}")
    private String restApiDocPath;
    @Value("${springdoc.swagger-ui.path}")
    private String swaggerPath;

    public SecurityConfig(Logger logger,
                          UsersRepository userRepo,
                          JwtTokenFilter jwtTokenFilter) {
        super();

        this.logger = logger;
        this.userRepo = userRepo;
        this.jwtTokenFilter = jwtTokenFilter;

        // Inherit security context in async function calls
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> userRepo
                .loadUserByUsername(username)
                // .orElseThrow( () -> new UsernameNotFoundException( String.format("User: %s, not found", username) ))
                );
    }

    // Set password encoding schema
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		  final CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("*"));
	        configuration.setAllowedMethods(Arrays.asList("*"));
	        configuration.setAllowedHeaders(Arrays.asList("*"));
	        
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	}

	// @Bean
	// public WebMvcConfigurer corsConfigurer() {
	// 	return new WebMvcConfigurer() {
	// 		@Override
	// 		public void addCorsMappings(CorsRegistry registry) {
	// 			registry
	// 			.addMapping("/**").allowedOrigins("*").allowedMethods("*")
	// 			// .allowedOrigins("http://localhost:4200")
	// 			;
	// 		}
	// 	};
	// }
}