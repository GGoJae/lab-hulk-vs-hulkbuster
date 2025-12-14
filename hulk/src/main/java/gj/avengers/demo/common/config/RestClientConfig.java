package gj.avengers.demo.common.config;

import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(HttpClientProps.class)
public class RestClientConfig {

//    // ✅ 한 곳에서만 관리할 “기준 타임아웃”
//    private static final Timeout CONNECT_TIMEOUT = Timeout.ofSeconds(3);     // TCP connect 성립까지
//    private static final Timeout RESPONSE_TIMEOUT = Timeout.ofSeconds(3);    // 응답 바이트 대기(=read timeout 성격)
//    private static final Timeout CONN_REQUEST_TIMEOUT = Timeout.ofSeconds(3);// 풀에서 커넥션 빌릴 때까지

    @Value("${hulkbuster.url}")
    private String hulkbusterBaseUrl;

    @Bean
    public ConnectionConfig connectionConfig(HttpClientProps props) {
        return ConnectionConfig.custom()
                        .setConnectTimeout(Timeout.of(props.timeout().connect()))    // ✅ TCP connect timeout
                        .build();
    }

    @Bean
    public PoolingHttpClientConnectionManager connectionManager(
            ConnectionConfig connectionConfig,
            HttpClientProps props) {
        return PoolingHttpClientConnectionManagerBuilder.create()
                // ✅ 전체 커넥션 풀 총량
                .setMaxConnTotal(props.pool().maxTotal())
                // ✅ 대상(host:port)별 최대 커넥션 수
                .setMaxConnPerRoute(props.pool().maxPerRoute())
                .setDefaultConnectionConfig(connectionConfig)
                .build();
    }

    @Bean
    public CloseableHttpClient httpClient(
            PoolingHttpClientConnectionManager connectionManager,
            HttpClientProps props) {

        // ✅ “요청 단위” 타임아웃(풀 대여/연결/응답대기)을 HttpClient 레벨에서 통합 관리
        RequestConfig requestConfig = RequestConfig.custom()
                // 풀에서 커넥션 대여 대기 시간
                .setConnectionRequestTimeout(Timeout.of(props.timeout().connectionRequest()))
                // 응답 바이트 대기 시간(=readTimeout 역할)
                .setResponseTimeout(Timeout.of(props.timeout().read()))
                .build();

        return HttpClients.custom()
                .setConnectionManager(connectionManager)

                // ✅ 기본 RequestConfig 적용(팩토리에 timeout 걸 필요가 줄어듦)
                .setDefaultRequestConfig(requestConfig)

                // ✅ 운영에서 꽤 중요한 옵션들
                .evictExpiredConnections()                  // 만료된 커넥션 제거
                .evictIdleConnections(Timeout.of(props.pool().idleEvict()))// 놀고 있는 커넥션 정리(예시)
                .build();
    }

    @Bean("hulkbusterRestTemplate")
    public RestTemplate hulkbusterRestTemplate(RestTemplateBuilder builder, CloseableHttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory factory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        return builder
                .rootUri(hulkbusterBaseUrl)
                .requestFactory(() -> factory)
                .build();
    }
}
