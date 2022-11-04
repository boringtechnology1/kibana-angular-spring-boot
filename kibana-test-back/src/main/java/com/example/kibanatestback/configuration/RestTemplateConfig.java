package com.example.kibanatestback.configuration;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfig {
    final static int READ_TIMEOUT = 60000;
    final static int CONN_TIMEOUT = 20000;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setBufferRequestBody(false);
        requestFactory.setHttpClient(HttpClientBuilder.create().build());
        requestFactory.setReadTimeout(READ_TIMEOUT);
        requestFactory.setConnectTimeout(CONN_TIMEOUT);
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }

}
