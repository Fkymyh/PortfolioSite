package com.example.portfolio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 公開ページと管理画面のアクセス制御を定義します。
 */
@Configuration
public class SecurityConfig {

	/** 公開ページを許可し、管理画面をADMIN権限で保護します。 */
	@Bean
	public SecurityFilterChain securityFilterChain(
			HttpSecurity http) throws Exception {

		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(
								"/",
								"/about",
								"/apps/**",
								"/learning",
								"/contact/**",
								"/css/**",
								"/images/**",
								"/js/**",
								"/error")
						.permitAll()
						.requestMatchers("/admin/**")
						.hasRole("ADMIN")
						.anyRequest()
						.permitAll())
				.formLogin(form -> form
						.defaultSuccessUrl("/admin/messages", true)
						.permitAll())
				.logout(logout -> logout
						.logoutSuccessUrl("/")
						.permitAll())
				.headers(headers -> headers
						.frameOptions(frame -> frame.sameOrigin()));
		return http.build();
	}

	/** 管理者パスワードを安全にハッシュ化するエンコーダーです。 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories
				.createDelegatingPasswordEncoder();
	}

	/** 環境変数から取得したパスワードで管理者ユーザーを作成します。 */
	@Bean
	public UserDetailsService userDetailsService(
			PasswordEncoder passwordEncoder,
			@Value("${portfolio.admin.password}") String adminPassword) {

		UserDetails admin = User.withUsername("admin")
				.password(passwordEncoder.encode(adminPassword))
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(admin);
	}

}
