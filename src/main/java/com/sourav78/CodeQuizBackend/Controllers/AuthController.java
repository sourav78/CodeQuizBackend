package com.sourav78.CodeQuizBackend.Controllers;

import com.sourav78.CodeQuizBackend.Entity.User;
import com.sourav78.CodeQuizBackend.Services.AuthService;
import com.sourav78.CodeQuizBackend.Utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Auth service dependency injection
    @Autowired
    AuthService authService;

    @GetMapping("")
    public String hello() {
        return "Hello CodeQuizBackend";
    }

    // Health Check API
    @GetMapping("/health")
    public ResponseEntity<Object> health(@RequestParam String flag) {

        if(flag.equals("give")){
            throw new RuntimeException("Custom Error");
        }

        return ResponseHandler.responseBuilder("CodeQuiz Backend is Up and Running", HttpStatus.OK, null);
    }

    // Register API
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        System.out.println("Falg1");
        String response = authService.register(user);
        return ResponseHandler.responseBuilder(response, HttpStatus.OK, null);
    }

}
