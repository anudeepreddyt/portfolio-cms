package com.portfolio.cms.controller;
import com.portfolio.cms.dto.*; import com.portfolio.cms.service.AuthService; import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/auth") @RequiredArgsConstructor
public class AuthController { private final AuthService service; @PostMapping("/login") public AuthResponse login(@Valid @RequestBody LoginRequest r){return service.login(r);} @PostMapping("/refresh") public AuthResponse refresh(@Valid @RequestBody RefreshRequest r){return service.refresh(r);} }
