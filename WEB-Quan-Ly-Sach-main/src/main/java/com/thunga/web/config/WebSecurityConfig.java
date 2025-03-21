package com.thunga.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.thunga.web.service.AccountService;

@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private AccountService accountService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(accountService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeRequests(auth -> auth
				.antMatchers("/", "/signup", "/login", "/bookImage", "/h2-console/**",
						 "/products", "/detail-product", "/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
				.antMatchers("/cart", "/orderList","/profile", "/view-cart", "/orders")
					.hasAnyRole("USER", "ADMIN")
				.antMatchers("/manage-product","/manage-order",
						"/manage-category", "/manage-user", "/manage-author")
					.hasRole("ADMIN")
				.antMatchers("/add-to-cart")
					.hasRole("USER")
				.anyRequest().authenticated()
			)
			.formLogin(form -> form
				.loginProcessingUrl("/j_spring_security_check")
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.failureUrl("/login?error=true")
				.usernameParameter("username")
				.passwordParameter("password")
			)
			.logout(logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
			)
			.headers(headers -> headers
				.frameOptions(frameOptions -> frameOptions.sameOrigin())
			);
		
		http.authenticationProvider(authenticationProvider());
		
		return http.build();
	}
}
	
	
