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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
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
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userName = authentication.getName();

        if(flag.equals("give")){
            throw new RuntimeException("Custom Error");
        }

        return ResponseHandler.responseBuilder("CodeQuiz Backend is Up and Running", HttpStatus.OK, null);

    }

    // Register API
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) {

        // Register the user and get the saved user
        User savedUser = authService.register(user);

        // Generate JWT token using saved user
        String jwt = jwtUtil.generateToken(savedUser.getUserName(), savedUser.getId());

        // Return success message with JWT token
        return ResponseHandler.responseBuilder("User Registered Successfully", HttpStatus.OK, jwt);
    }


    // Login API
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());

        //Check if user is verified or not
        if(!authService.isUserVerified(user.getUserName())){
            throw new BadCredentialsException("User is not verified");
        }

        long userId = userDetailsService.getUserIdByUsername(user.getUserName());
        String jwt = jwtUtil.generateToken(userDetails.getUsername(), userId);
        return ResponseHandler.responseBuilder("User login successfully", HttpStatus.OK, jwt);
    }

    // Verify User API
    @PostMapping("/verify")
    public ResponseEntity<Object> verifyUser(@RequestBody User user) {
        authService.verifyUser(user.getEmail(), user.getVerificationCode());
        return ResponseHandler.responseBuilder("User Verified Successfully", HttpStatus.OK, null);
    }

    // Resend Verification Code API
    @GetMapping("/resend-verification")
    public ResponseEntity<Object> resendVerification(@RequestParam String email) {

        //Check if email is empty
        if(email == null || email.isEmpty()){
            throw new BadCredentialsException("Email is required");
        }
        // Resend verification code
        authService.resendVerificationCode(email);

        return ResponseHandler.responseBuilder(
                "Verification code sent successfully",
                HttpStatus.OK,
                null
        );
    }

}
