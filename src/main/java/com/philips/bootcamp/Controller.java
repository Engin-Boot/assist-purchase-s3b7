package com.philips.bootcamp;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class Controller {

    @RequestMapping("/hello")
    public String hello(){
        return "hello11";
    }
}
