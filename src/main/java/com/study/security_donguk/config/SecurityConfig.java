package com.study.security_donguk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

import com.study.security_donguk.config.auth.AuthFailureHandler;
import com.study.security_donguk.service.auth.PrincipalOauth2UserService;

import lombok.RequiredArgsConstructor;

// 상속받은거 말고 우리가 만들어 쓰겠다.
@EnableWebSecurity
//IoC 등록
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final CorsFilter corsFilter;
	
	private final PrincipalOauth2UserService principalOauth2UserService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.headers()
			.frameOptions()
			.disable();
		http.addFilter(corsFilter);
		
		http.authorizeRequests()
			.antMatchers("/api/v1/grant/test/user/**")
			.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			
			.antMatchers("/api/v1/grant/test/manager/**")
			.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			
			.antMatchers("/api/v1/grant/test/admin/**")
			.access("hasRole('ROLE_ADMIN')")
			
			.antMatchers("/notice/addition","/notice/modification/**")
			.hasRole("ADMIN")
			
			.antMatchers("/","index","/mypage/**")	//우리가 지정한 요청
			.authenticated()						//인증을 거쳐라
			
			.anyRequest()							//다른 모든 요청들은
			.permitAll()							//모두 접근 권한을 부여하겠다.
			.and()						
			.formLogin()							//로그인방식은 form 로그인을 사용하겠다.
			.loginPage("/auth/signin")				//로그인 페이지는 해당 get요청을 통해 접근하겠다.
			.loginProcessingUrl("/auth/signin")		//로그인 요청(post 요청)
			.failureHandler(new AuthFailureHandler())
			
			.and()
			.oauth2Login()
			.userInfoEndpoint()
			.userService(principalOauth2UserService)
			.and()
			
			.defaultSuccessUrl("/index");			
	}
}
