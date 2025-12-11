package gj.avengers.demo.common.config;

import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestClientConfig {

    @Bean
    public RestTemplate restTemplate() {

        CloseableHttpClient httpClient = createHttpClient();

        var factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(Duration.ofSeconds(2));
        factory.setReadTimeout(Duration.ofSeconds(3));
        factory.setHttpClient(httpClient);

        return new RestTemplate(factory);
    }

    private CloseableHttpClient createHttpClient() {
        var connectionManager = new PoolingHttpClientConnectionManager();

        connectionManager.setMaxTotal(50);
        connectionManager.setDefaultMaxPerRoute(25);

        ConnectionConfig connectionConfig = ConnectionConfig.custom().setConnectTimeout(Timeout.ofSeconds(2)).build();
        connectionManager.setDefaultConnectionConfig(connectionConfig);

        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();

    }
}
