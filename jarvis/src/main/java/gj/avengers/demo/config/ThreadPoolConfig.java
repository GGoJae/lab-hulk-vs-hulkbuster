package gj.avengers.demo.config;

import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor taskTreadPool() {
        return new ThreadPoolTaskExecutorBuilder()
                .corePoolSize(8)
                .maxPoolSize(16)
                .queueCapacity(100)
                .keepAlive(Duration.ofMinutes(1))
                .threadNamePrefix("async-")
                .build();
    }
}
