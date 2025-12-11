package gj.avengers.demo.common.util.externalCall;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.util.function.Supplier;

@Component
public class ExternalCallRetryExecutor {

    @Retryable(
            retryFor = RestClientException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 500)
    )
    public <T> T execute(Supplier<T> supplier) {
        return supplier.get();
    }
}
