package info.caprese.gnocchi;


import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories
@EnableAsync
@EnableScheduling
@Slf4j
public class GnocchiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GnocchiApplication.class, args);
    }

//    @Bean
//    public CloseableHttpClient  client() {
//        log.info("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
//        return  HttpClients.custom()
//                .setDefaultRequestConfig(RequestConfig.custom()
//                        .setCookieSpec(CookieSpecs.IGNORE_COOKIES).build())
//                .build();
//    }
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
//    }

}
