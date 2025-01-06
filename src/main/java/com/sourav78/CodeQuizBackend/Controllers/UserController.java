package com.sourav78.CodeQuizBackend.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @RequestMapping("")
    public String hello(HttpServletRequest request) {
        long userId = (long) request.getAttribute("userId");
        return "Hello From user side" + userId;
    }
}
