package gj.avengers.demo.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "http.client")
public record HttpClientProps(
        Pool pool,
        Timeout timeout
) {
    public record Pool(int maxTotal, int maxPerRoute, Duration idleEvict) {}
    public record Timeout(Duration connect, Duration read, Duration connectionRequest) {}
}
