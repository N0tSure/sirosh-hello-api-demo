package com.sirosh.spsec.key;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController {

    @GetMapping("/api/hello")
    String hello() {
        return "Hello World!";
    }
}
