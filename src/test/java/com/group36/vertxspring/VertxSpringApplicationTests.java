package com.group36.vertxspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VertxSpringApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testvertx(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity("http://localhost:8080/articles", String.class);

        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}
