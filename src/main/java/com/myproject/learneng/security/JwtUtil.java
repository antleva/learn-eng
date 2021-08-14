package com.myproject.learneng.security;

import com.myproject.learneng.domain.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import static io.jsonwebtoken.Jwts.parserBuilder;

@Service
public class JwtUtil {
    private static final Logger logger = Logger.getLogger(JwtUtil.class);

    @Value("${secret}")
    private String secret;

    @Value("${jwtTokenValidity}")
    private long jwtTokenValidity;

    private byte[] b = new byte[36];

    private Key key;

    private UserDetailsService userDetailsService;

    @Autowired
    public JwtUtil(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        byte[] bytes = secret.getBytes();
        System.arraycopy(bytes,0,b,0,bytes.length);
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        key = Keys.hmacShaKeyFor(b);
    }

    public String createToken(String username, Set<Role> roles) {
            Claims claims = Jwts.claims().setSubject(username);
            claims.put("roles", getRoleNames(roles));
            logger.info("create bearer token for user "+username);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(jwtTokenValidity)))
                .signWith(key)
                .compact();
    }

    public Authentication getAuthentication(String token) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsernameFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getUsernameFromToken(String token) {
            logger.info("retrieve username from bearer token");
        return parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req){
            logger.info("retrieve bearer token from request");
            String bearerToken = req.getHeader("Authorization");
            if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                return bearerToken.substring(7);
            }

        return null;
    }

    public boolean validateToken(String jwt) {
            logger.info("validate bearer token");
            parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
        return true;
    }

    private Set<String> getRoleNames(Set<Role> userRoles) {
            Set<String> result = userRoles.stream().map(role -> role.getRoleName().name()).collect(Collectors.toSet());
        return result;
    }
    public long getExpirationTime(){
        return jwtTokenValidity;
    }
}
