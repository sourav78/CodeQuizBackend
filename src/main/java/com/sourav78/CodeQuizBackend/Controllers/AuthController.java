package com.sourav78.CodeQuizBackend.Controllers;

import com.sourav78.CodeQuizBackend.Entity.User;
import com.sourav78.CodeQuizBackend.Services.AuthService;
import com.sourav78.CodeQuizBackend.Services.UserDetailsServiceImpl;
import com.sourav78.CodeQuizBackend.Utils.JWTUtils;
import com.sourav78.CodeQuizBackend.Utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JWTUtils jwtUtil;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        if(flag.equals("give")){
            throw new RuntimeException("Custom Error");
        }

        return ResponseHandler.responseBuilder("CodeQuiz Backend is Up and Running"+userName, HttpStatus.OK, null);

    }

    // Register API
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        String response = authService.register(user);
        return ResponseHandler.responseBuilder(response, HttpStatus.OK, null);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseHandler.responseBuilder("Login successfully", HttpStatus.OK, jwt);
    }

}
