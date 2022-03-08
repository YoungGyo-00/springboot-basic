package com.example.springbootbasic;

import com.example.springbootbasic.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootBasicApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("----------");

        var objectMapper = new ObjectMapper();

        // object -> Text
        // object mapper get method 를 활용한다.
        var user = new User("steve", 10, "패스트 캠퍼스", "여기다");

        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);

        // text -> object
        // default 생성자가 필요함
        var objectUser = objectMapper.readValue(text, User.class);
        System.out.println(objectUser);
    }

}
