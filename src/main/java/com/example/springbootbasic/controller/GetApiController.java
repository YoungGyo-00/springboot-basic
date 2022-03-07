package com.example.springbootbasic.controller;

import com.example.springbootbasic.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable(name = "name") String name){
        System.out.println("PathVariable : " + name);
        return name;
    }

    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){

        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach( entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        });

        return sb.toString();

    }

    @GetMapping("query-param2")
    public String queryParam2(
            @RequestParam String name,
            @RequestParam String email
    ){
        System.out.println(name);
        System.out.println(email);

        return name + " " + email;
    }

    @GetMapping("query-param3")
    public String queryParam3(UserRequest userRequest){
        System.out.println(userRequest.getAge());
        System.out.println(userRequest.getEmail());
        System.out.println(userRequest.getName());

        return userRequest.toString();
    }
}
