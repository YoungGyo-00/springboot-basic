package com.example.server.controller;

import com.example.server.dto.Req;
import com.example.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    // https://openapi.naver.com/v1/search/local.json
    // ?query="UTF-8" encoding
    // &display=10
    // &start=1
    // &sort=random
    @GetMapping("/naver")
    public String naver(){
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", "갈비집")
                .queryParam("display", "10")
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode()
                .build()
                .toUri();

//        log.info("uri : {}", uri);

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "iexMfbEWOBJq3t0SC00_")
                .header("X-Naver-Client-Secret", "gWEqFMOlhY")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);
        return result.getBody();
    }

    @GetMapping("hello")
    public User hello(@RequestParam String name, @RequestParam int age){
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    // postforentity에 의한 요청
    /*@PostMapping("/user/{userId}/name/{userName}")
    public User post(@RequestBody User user, @PathVariable int userId, @PathVariable String userName){
      log.info("userId : {}, userName : {}", userId, userName);
      log.info("client req : {}", user);

      return user;
    }*/

    // exchange에 의한 요청
    /*@PostMapping("/user/{userId}/name/{userName}")
    public User post(@RequestBody User user,
                     @PathVariable int userId,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,
                     @RequestHeader("custom-header") String customHeader
    ){
        log.info("authoization : {}, custom : {}", authorization, customHeader);
        log.info("userId : {}, userName : {}", userId, userName);
        log.info("client req : {}", user);

        return user;
    }*/

    // 여러 모델에 따른 객체 호출
    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(
//                     HttpEntity<String> entity,
                     @RequestBody Req<User> user,
                     @PathVariable int userId,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,
                     @RequestHeader("custom-header") String customHeader
    ){
//        log.info("req : {}", entity.getBody());
        log.info("authoization : {}, custom : {}", authorization, customHeader);
        log.info("userId : {}, userName : {}", userId, userName);
        log.info("client req : {}", user);

        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header()
        );
        response.setResBody(
                user.getResBody()
        );

        return response;
    }
}
