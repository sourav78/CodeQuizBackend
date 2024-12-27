package com.sourav78.CodeQuizBackend.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @RequestMapping("")
    public String hello() {
        return "Hello From user side";
    }
}
