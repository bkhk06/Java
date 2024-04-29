package com.java.apiaccess;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiCheckService {

    private final RestTemplate restTemplate;

    public ApiCheckService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean checkApi() {
        String apiUrl = "https://www.bing.com"; // 替换为你要检测的API地址
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        // 检测API正常性、响应速度和返回信息是否合理
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return true;
        } else {
            return false;
        }
    }
}

