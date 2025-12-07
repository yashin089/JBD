package com.example.jbd.controller;

import com.example.jbd.dto.request.RefreshTokenRequest;
import com.example.jbd.dto.request.SignInRequest;
import com.example.jbd.dto.request.SignUpRequest;
import com.example.jbd.dto.response.exception.AlreadyExistsException;
import com.example.jbd.dto.response.exception.InvalidCredentialsException;
import com.example.jbd.dto.response.exception.InvalidJwtException;
import com.example.jbd.dto.response.message.JwtAuthenticationResponse;
import com.example.jbd.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest signUpRequest) throws AlreadyExistsException {
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest signInRequest) throws InvalidJwtException, InvalidCredentialsException {
        return authenticationService.signIn(signInRequest);
    }

    @PostMapping("/refresh")
    public JwtAuthenticationResponse refreshAccessToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws InvalidJwtException {
        return authenticationService.refreshAccessToken(refreshTokenRequest);
    }
}
