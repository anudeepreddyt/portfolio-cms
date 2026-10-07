package com.portfolio.cms.security;

import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service;
import javax.crypto.SecretKey; import java.nio.charset.StandardCharsets; import java.util.Date;

@Service
public class JwtService {
    private final SecretKey key; private final long accessExpiration; private final long refreshExpiration;
    public JwtService(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.access-expiration-ms}") long accessExpiration,@Value("${app.jwt.refresh-expiration-ms}") long refreshExpiration){
        if(secret.getBytes(StandardCharsets.UTF_8).length < 32) throw new IllegalStateException("JWT_SECRET must be at least 32 bytes");
        this.key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); this.accessExpiration=accessExpiration; this.refreshExpiration=refreshExpiration;
    }
    public String accessToken(String subject){ return build(subject,"ACCESS",accessExpiration); }
    public String refreshToken(String subject){ return build(subject,"REFRESH",refreshExpiration); }
    private String build(String subject,String type,long ttl){ Date now=new Date(); return Jwts.builder().subject(subject).claim("type",type).issuedAt(now).expiration(new Date(now.getTime()+ttl)).signWith(key).compact(); }
    public Claims parse(String token){ return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload(); }
    public boolean valid(String token,String type){ try { Claims c=parse(token); return type.equals(c.get("type",String.class)); } catch(JwtException|IllegalArgumentException e){ return false; } }
    public long getAccessExpiration(){return accessExpiration;}
}
