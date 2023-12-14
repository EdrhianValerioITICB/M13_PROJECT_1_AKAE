package cat.iticbcn.demo.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

/*
Responsible for generating JWT tokens when a user successfully logs in
 */
@Component
public class JwtTokenProvider {

    Logger log = LoggerFactory.getLogger(this.getClass());


    @Value("${app.security.jwt.secret}")
    private String jwtSecret;

    @Value("${app.security.jwt.expiration}")
    private Long jwtDurationSeconds;

    public String generateToken(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", "JWT")
                .setSubject(Long.toString(user.getId()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (jwtDurationSeconds * 1000)))
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .compact();

    }

    public boolean isValidToken(String token) {
        if (!StringUtils.hasLength(token))
            return false;

        try {
            JwtParser validator = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                    .build();

            validator.parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.info("Error in the signature of the token", e);
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            log.info("Token incorrect", e);
        } catch (ExpiredJwtException e) {
            log.info("Token expired", e);
        }
        return false;

    }

    public String getUsernameFromToken(String token) {
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build();

        Claims claims = parser.parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }
}

