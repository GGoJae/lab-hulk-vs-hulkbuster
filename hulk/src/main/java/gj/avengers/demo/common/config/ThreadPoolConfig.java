package gj.avengers.demo.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.boot.task.ThreadPoolTaskSchedulerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.Duration;

@Slf4j
@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolTaskScheduler taskScheduler(ThreadPoolTaskSchedulerBuilder builder) {
        return builder.poolSize(2)
                .threadNamePrefix("scheduler-")
                .additionalCustomizers(s -> {
                    s.setErrorHandler(t -> log.error("스케쥴러 Task 에러 ", t));
                })
                .build();
    }

    @Bean("externalApiExecutor")
    public ThreadPoolTaskExecutor externalApiExecutor(ThreadPoolTaskExecutorBuilder builder) {
        return builder.threadNamePrefix("api-")
                .corePoolSize(8)
                .maxPoolSize(16)
                .queueCapacity(100)
                .keepAlive(Duration.ofSeconds(60))
                .build();
    }
}
