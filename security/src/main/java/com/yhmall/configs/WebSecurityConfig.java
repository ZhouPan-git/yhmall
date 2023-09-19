package com.yhmall.configs;


import com.yhmall.controller.JwtAuthenticationEntryPoint;
import com.yhmall.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//出错了的处理类
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	public static void main(String[] args) {
		System.out.println( new BCryptPasswordEncoder().encode("a") );
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		//配置AuthenticationManager,使其知道从何处(jwtUserDetailsService)加载匹配凭据的用户
		//在匹配时使用BCryptPasswordEncoder
		//auth类组装jwtUserDetailsService(它提供了一个根据用户名加载用户信息)
		//组装加密算法 => AuthenticationManager
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	//加密算法类
	// @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		//关闭csrf跨站请求伪造
		httpSecurity.csrf().disable()
				// dont authenticate this particular request
				//不验证此特定请求

				.authorizeRequests().antMatchers(
						 "/bbssecurity/user/code.action",

				                    "/email/sendEmail",
									"/security/user/backpwd.action",
									"/security/user/reg.action",
									"/security/user/login.action",
						            "/bbssecurity/user/loginCheck",
									"/swagger-ui/**",
									"/swagger-ui.html",
									"/api/**",
									"/swagger-resources/**",
									// "/v2/api-docs",
									"/v3/api-docs",
									"/webjars/**"
				).permitAll().
				// all other requests need to be authenticated
				//		所有其他请求都需要经过身份验证
						anyRequest().authenticated().and().
				// make sure we use stateless session; session won't be used to
				// store user's state.
				//		验证出错则回401
						exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				//		确保我们使用无状态会话,会话不会用于存储用户的状态
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		//添加一个筛选器以验证每个请求的令牌
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
