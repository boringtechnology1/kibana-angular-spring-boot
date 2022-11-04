package com.example.kibanatestback.service;

import com.example.kibanatestback.model.ElkLoginParamsRequestDTO;
import com.example.kibanatestback.model.ElkLoginRequestDTO;
import com.example.kibanatestback.model.ElkProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class KibanaServiceImpl implements KibanaService {

    private final ElkProperties elk1 = ElkProperties.builder()
            .url("http://localhost:5601")
            .username("elastic")
            .password("BwNhbb6cbaiE+u7Ii2l-")
            .kbnDashboardPath("/app/dashboards#/view/722b74f0-b882-11e8-a6d9-e546fe2bba5f?embed=true&_g=(filters%3A!()%2CrefreshInterval%3A(pause%3A!t%2Cvalue%3A0)%2Ctime%3A(from%3Anow-7d%2Cto%3Anow))&hide-filter-bar=true")
            .kbnVersion("8.4.1")
            .build();

    private final Map<String, ElkProperties> userElkMap = Map.of("user1", elk1);

    private final HashMap<String, String> elkCookieMap = new HashMap<>();

    private final RestTemplate restTemplate;

    public KibanaServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HttpEntity<String> elkLogin(ElkProperties elkProperties) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("kbn-version", elkProperties.getKbnVersion());
        headers.add("kbn-xsrf", "true");

        String authUrl = elkProperties.getUrl() + "/internal/security/login";
        ElkLoginRequestDTO body = new ElkLoginRequestDTO();
        body.setParams(new ElkLoginParamsRequestDTO(elkProperties.getUsername(), elkProperties.getPassword()));

        try {
            return restTemplate.exchange(
                    authUrl,
                    HttpMethod.POST,
                    new HttpEntity<>(body, headers),
                    String.class
            );

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }

    public String getElkCookie(String username) {
        try {
            if (elkCookieMap.containsKey(username)) {
                return elkCookieMap.get(username);
            }
            HttpEntity<String> httpEntity = elkLogin(userElkMap.get(username));
            String cookie = httpEntity.getHeaders().getFirst("Set-Cookie");
            elkCookieMap.put(username, cookie);
            return cookie;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public ResponseEntity<String> getDashboard() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", getElkCookie(user.getUsername()));
        return new ResponseEntity<>(userElkMap.get(user.getUsername()).getKbnDashboardPath(), headers, HttpStatus.OK);
    }

}
