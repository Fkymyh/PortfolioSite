package com.example.portfolio.config;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 開発環境だけでH2 Consoleへのアクセスを許可します。
 */
@Profile("dev")
@Configuration
public class H2ConsoleSecurityConfig {

	/** H2 Console専用のセキュリティ設定を最優先で適用します。 */
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain h2ConsoleSecurityFilterChain(
			HttpSecurity http) throws Exception {

		http
			.securityMatcher(
				PathRequest.toH2Console()
			)
			.authorizeHttpRequests(auth -> auth
				.anyRequest()
				.permitAll()
			)
			.csrf(csrf -> csrf.disable())
			.headers(headers -> headers
				.frameOptions(frame -> frame.sameOrigin())
			);

		return http.build();
	}
}
