package com.example.demo.Config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
	@Bean("csvAsyncConfig")
	public Executor asyncTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(15);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(5);
		executor.setThreadNamePrefix("csvAsyncProcessor-");
		executor.initialize();
		return executor;
	}

	@Bean("imageAsyncConfig")
	public Executor imageAsyncConfigExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(15);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(5);
		executor.setThreadNamePrefix("imageTaskAsyncProcessor-");
		executor.initialize();
		return executor;
	}
	// 15 threads
	// 15 jobs -> running
	// 10
	// 16th task - 1
}
