package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    // 레거시(?) RequestMapping - GET
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        return "Hello World";
    }

    // 매개변수 없는 GET 메서드 구현
    @GetMapping(value = "/name")
    public String getName() {
        return "Flature";
    }

    // 매개변수에 @PathVariable 을 넣어 활용한 GET 메서드 구현
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        return variable;
    }

    // @RequestParam 을 활용한 GET 메서드 구현
    @GetMapping(value = "/requestParam")
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization) {
        return name + " " + email + organization;
    }

    // Map 으로 RequestParam 받기
    @GetMapping(value = "/request2")
    public String getVariable2(@RequestParam Map<String, String> param) {
        param.entrySet().forEach((map)->{
            System.out.printf("key:%s value:%s\n",map.getKey(),map.getValue());
        });
        return "request2가 호출 완료 되었습니다";
    }


    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDto) {
        return memberDto.toString();
    }
}
