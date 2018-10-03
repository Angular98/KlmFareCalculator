package com.fare.business;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fare.business.authenticationdetails.CustomUserDetails;
import com.fare.business.repository.UserRepository;

@Configuration
public class FareCalculatorBusinessServiceBeanFactory {

	@Bean
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository userRepo) throws Exception {

		builder.inMemoryAuthentication().withUser("travel-api-client").password("psw");
		builder.userDetailsService(new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

				return new CustomUserDetails(userRepo.findByUserName(username));
			}

		});
	}

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("FareDetailsService-");
		executor.initialize();
		return executor;
	}

}
