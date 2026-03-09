package com.example.ecomove;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:mysql://localhost:3306/ecomove?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
        "spring.datasource.username=root",
        "spring.datasource.password=root",
        "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver"
})
class EcomoveApplicationTests {

    @Test
    void contextLoads() {
    }
}