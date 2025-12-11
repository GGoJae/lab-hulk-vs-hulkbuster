package gj.avengers.demo.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executor;

@Configuration
@RequiredArgsConstructor
public class ReactorSchedulerConfig {

    @Qualifier("externalApiExecutor")
    private final Executor externalApiExecutor;

    @Bean
    public Scheduler apiScheduler() {
        return Schedulers.fromExecutor(externalApiExecutor);
    }
}
