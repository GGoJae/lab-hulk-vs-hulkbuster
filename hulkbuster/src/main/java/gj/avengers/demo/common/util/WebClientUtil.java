package gj.avengers.demo.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WebClientUtil {

    public static <T> Mono<T> defaultPolicy(Mono<T> mono) {
        return mono.retryWhen(Retry.backoff(3, Duration.ofMillis(500))
                .filter(t -> t instanceof WebClientRequestException ||
                        (t instanceof WebClientResponseException r && r.getStatusCode().is5xxServerError())
                )
        );
    }
}
