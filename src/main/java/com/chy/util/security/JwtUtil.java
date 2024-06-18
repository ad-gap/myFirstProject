package com.chy.util.security;

import com.chy.util.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Autowired
    private RedisUtil redisUtil;

    private long tokenEcpiration = 1000 * 60 * 60 * 240;

    private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String token = createToken(claims, userDetails.getUsername());
        redisUtil.set(userDetails.getUsername(), token, tokenEcpiration); // Token存入Redis，设置有效期为10天
        return token;
    }

    private String createToken(Map<String, Object> claims, String username) {
        String token = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+tokenEcpiration))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        if (isTokenExpired(token)) {
            return false;
        }
        final String redisToken = redisUtil.get(username);
        return (username.equals(userDetails.getUsername()) && token.equals(redisToken));
    }
}
