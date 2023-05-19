package com.HabibDev.BookShopApplication.config;

import com.HabibDev.BookShopApplication.entity.Role;
import com.HabibDev.BookShopApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/user/register")
                .permitAll()
                .requestMatchers("/user/login")
                .permitAll()
                .requestMatchers("/books/create","/books/update/{bookId}","/books/delete/{bookId}").hasAuthority("ADMIN")
                .requestMatchers("/books/all","/author/{authorName}/{bookName}","/books/id/{bookId}","/author/{authorName}").hasAnyAuthority("CUSTOMER","ADMIN")

                /*
                .requestMatchers("/books/id/**").hasAnyAuthority("ADMIN", "CUSTOMER")
                .requestMatchers("/books/all").hasAnyAuthority("ADMIN", "CUSTOMER")
                .requestMatchers("/books/author/**").hasAnyAuthority("ADMIN", "CUSTOMER")
                .requestMatchers("/books/{authorName}/{bookName}").hasAnyAuthority("ADMIN","CUSTOMER")
                .requestMatchers("/books/create").hasAuthority("ADMIN")
                .requestMatchers("/books/update/**").hasAuthority("ADMIN")
                .requestMatchers("/books/delete/**").hasAuthority("ADMIN")
                 .requestMatchers("/user/update").hasAuthority("USER")
               .requestMatchers("/user/delete").hasAuthority("ADMIN")
                 .requestMatchers("/user/update").hasAnyAuthority("admin","user")
                  */

                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }
}
