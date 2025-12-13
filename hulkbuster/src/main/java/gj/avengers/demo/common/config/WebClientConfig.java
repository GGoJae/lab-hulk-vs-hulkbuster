package gj.avengers.demo.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final HttpClient myHttpClient;

    @Value("${jarvis.url}")
    private String jarvisBaseUrl;

    @Value("${veronica.url}")
    private String veronicaBaseUrl;

    @Bean
    public WebClient jarvisWebClient() {
        return WebClient.builder()
                .baseUrl(jarvisBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(myHttpClient))
                .filter(((request, next) -> {
                    log.info("Request: {} {}", request.method(), request.url());
                    return next.exchange(request)
                            .doOnNext(response -> {
                                log.info("Response: {}", response.statusCode());
                            })
                            .doOnError(e -> log.error("Jarvis 호출 중 에러", e));
                }))
                .build();
    }

    @Bean
    public WebClient veronicaWebClient() {
        return WebClient.builder()
                .baseUrl(veronicaBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(myHttpClient))
                .filter((((request, next) -> {
                    log.info("Request: {} {}", request.method(), request.url());
                    return next.exchange(request)
                            .doOnNext(response -> {
                                log.info("Response: {}", response.statusCode());
                            })
                            .doOnError(e -> log.error("Veronica 호출 중 에러", e));
                })))
                .build();
    }

}
