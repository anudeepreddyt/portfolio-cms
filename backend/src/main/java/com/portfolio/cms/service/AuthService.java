package com.portfolio.cms.service;
import com.portfolio.cms.dto.*; import com.portfolio.cms.entity.User; import com.portfolio.cms.repository.UserRepository; import com.portfolio.cms.security.JwtService; import lombok.RequiredArgsConstructor; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.stereotype.Service;
@Service @RequiredArgsConstructor
public class AuthService {
    private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
    public AuthResponse login(LoginRequest r){ User u=users.findByEmail(r.email().toLowerCase()).orElseThrow(()->new IllegalArgumentException("Invalid email or password")); if(!encoder.matches(r.password(),u.getPasswordHash())) throw new IllegalArgumentException("Invalid email or password"); return tokens(u.getEmail()); }
    public AuthResponse refresh(RefreshRequest r){ if(!jwt.valid(r.refreshToken(),"REFRESH")) throw new IllegalArgumentException("Invalid or expired refresh token"); String email=jwt.parse(r.refreshToken()).getSubject(); if(users.findByEmail(email).isEmpty()) throw new IllegalArgumentException("User not found"); return tokens(email); }
    private AuthResponse tokens(String email){return new AuthResponse(jwt.accessToken(email),jwt.refreshToken(email),"Bearer",jwt.getAccessExpiration()/1000);}
}
