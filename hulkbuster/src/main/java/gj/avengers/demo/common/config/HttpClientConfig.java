package gj.avengers.demo.common.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.time.Duration;

@Configuration
public class HttpClientConfig {

    @Bean
    public HttpClient myHttpClient() {
        ConnectionProvider provider = ConnectionProvider.builder("external-pool")
                .maxConnections(50)
                .maxIdleTime(Duration.ofSeconds(30))
                .maxLifeTime(Duration.ofSeconds(60))
                .pendingAcquireTimeout(Duration.ofSeconds(5))
                .build();

        return HttpClient.create(provider)
                .responseTimeout(Duration.ofSeconds(3))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                .wiretap("reactor.netty.http.client.HttpClient",
                        LogLevel.DEBUG,     // 운영에는 Info 정도로 바꾸기
                        AdvancedByteBufFormat.TEXTUAL);
    }
}
