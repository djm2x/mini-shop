package com.example.minishop.configuration.security;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import com.example.minishop.repositories.UsersRepository;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

        private final Logger logger;
        private final UsersRepository userRepo;
        private final JwtTokenFilter jwtTokenFilter;

        @Value("${springdoc.api-docs.path}")
        private String restApiDocPath;
        @Value("${springdoc.swagger-ui.path}")
        private String swaggerPath;

        public SecurityConfig(Logger logger, UsersRepository userRepo, JwtTokenFilter jwtTokenFilter) {
                super();

                this.logger = logger;
                this.userRepo = userRepo;
                this.jwtTokenFilter = jwtTokenFilter;

                // Inherit security context in async function calls
                SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                // auth.userDetailsService(username -> userRepo
                // .findByUsername(username)
                // .orElseThrow( () -> new UsernameNotFoundException( String.format("User: %s,
                // not found", username) ))
                // );

                super.configure(auth);
        }

        // Set password encoding schema
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                // Enable CORS and disable CSRF
                http = http.cors().and().csrf().disable();

                // Set session management to stateless
                http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

                // Set unauthorized requests exception handler
                http = http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
                        logger.error("Unauthorized request - {}", ex.getMessage());
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                }).and();

                // Set permissions on endpoints
                http.authorizeRequests()
                                // Swagger endpoints must be publicly accessible
                                .antMatchers("/").permitAll().antMatchers(String.format("%s/**", restApiDocPath))
                                .permitAll().antMatchers(String.format("%s/**", swaggerPath)).permitAll()
                                // Our public endpoints
                                .antMatchers("/api/accounts/**").permitAll()
                                // .antMatchers(HttpMethod.GET, "/api/author/**").permitAll()
                                // .antMatchers(HttpMethod.POST, "/api/author/search").permitAll()
                                // .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
                                // .antMatchers(HttpMethod.POST, "/api/book/search").permitAll()
                                // Our private endpoints
                                .anyRequest().authenticated();

                // Add JWT token filter
                http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
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
        // return new WebMvcConfigurer() {
        // @Override
        // public void addCorsMappings(CorsRegistry registry) {
        // registry
        // .addMapping("/**").allowedOrigins("*").allowedMethods("*")
        // // .allowedOrigins("http://localhost:4200")
        // ;
        // }
        // };
        // }
}