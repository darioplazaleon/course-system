package com.example.coursesystem.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.coursesystem.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

@Component
public class JwtAuthenticationProvider {

    @Value("${jwt.secret.key}")
    private String secretKey;

    private HashMap<String, UserDTO> listTokens = new HashMap<>();

    public String createToken(UserDTO user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String token = JWT.create()
                .withClaim("id", user.id())
                .withClaim("email", user.email())
                .withClaim("role", user.role().toString())
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(algorithm);

        listTokens.put(token, user);

        return token;
    }

    public Authentication validateToken(String token) throws AuthenticationException {
        JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);

        HashSet<SimpleGrantedAuthority> rolesAndAuthorities = new HashSet<>();

        UserDTO exist = listTokens.get(token);
        if (exist == null) {
            throw new BadCredentialsException("User not registered");
        }

        rolesAndAuthorities.add(new SimpleGrantedAuthority("ROLE_" + exist.role()));
        return new UsernamePasswordAuthenticationToken(exist, token, rolesAndAuthorities);
    }

    public String deleteToken(String token) {
        if (!listTokens.containsKey(token)) {
            return "Token not found";
        }

        listTokens.remove(token);
        return "Session closed";
    }
}


