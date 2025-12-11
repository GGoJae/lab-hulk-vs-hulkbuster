package gj.avengers.demo.common.util.externalCall;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

@Component
public class ExternalCallExecutor {

    private final Executor externalApiExecutor;
    private final ExternalCallRetryExecutor retryExecutor;

    public ExternalCallExecutor(@Qualifier("externalApiExecutor")Executor externalApiExecutor, ExternalCallRetryExecutor retryExecutor) {
        this.externalApiExecutor = externalApiExecutor;
        this.retryExecutor = retryExecutor;
    }

    public <T> CompletableFuture<T> executeAsync(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(
                () -> retryExecutor.execute(supplier)
        , externalApiExecutor);
    }

}
