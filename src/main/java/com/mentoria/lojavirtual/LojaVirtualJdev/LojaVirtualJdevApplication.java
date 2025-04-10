package com.mentoria.lojavirtual.LojaVirtualJdev;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAsync
public class LojaVirtualJdevApplication implements AsyncConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualJdevApplication.class, args);
		System.out.println("Rodouu");
		System.out.println(new BCryptPasswordEncoder().encode("123"));

	}
	
	@Override
	@Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("MyExecutor-");
        executor.initialize();
        return executor;

}
    
}   
