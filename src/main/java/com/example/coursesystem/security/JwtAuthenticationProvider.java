package com.example.coursesystem.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.coursesystem.dto.user.UserDTO;
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

    public Long getUserId(String token) {
        String tokenJwt = token.split(" ")[1];
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(secretKey)).build().verify(tokenJwt);
        return jwt.getClaim("id").asLong();
    }

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
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
            Long userId = decodedJWT.getClaim("id").asLong();
            String role = decodedJWT.getClaim("role").asString();

            UserDTO user = listTokens.get(token);
            if (user == null) {
                throw new BadCredentialsException("User not registered");
            }

            HashSet<SimpleGrantedAuthority> rolesAndAuthorities = new HashSet<>();
            rolesAndAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));

            return new UsernamePasswordAuthenticationToken(user, token, rolesAndAuthorities);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid token", e);
        }
    }

    public String deleteToken(String token) {
        if (!listTokens.containsKey(token)) {
            return "Token not found";
        }

        listTokens.remove(token);
        return "Session closed";
    }
}


