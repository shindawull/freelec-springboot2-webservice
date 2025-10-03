package com.dawull.book.springboot.web;

import com.dawull.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @RestController 는 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌.
 * 이전엔 @ResponseBody를 각 메소드에 선언했던것을 한번에 사용할 수 있게 해준다.
 * @GetMapping 은 Http Method인 Get 요청을 받을 수 있는 api를 만들어줌.
 * 이전엔 @ReqestMapping(Method = RequestMethod.GET) 으로 사용
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

}
