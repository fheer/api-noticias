package com.example.demo.util;

import com.example.demo.entities.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.Charset;
import java.util.Date;

public class JsonWebToken {

    private long validityInMilliseconds = 360;

    // Content super secret key.
    private String secretKey = "uab_24_2022_tecnologias_en_internet";

    /**
     * Allows to create a new Json Web Token.
     * @param userEntity object.
     * @return token created.
     */
    public String createJwt(UserEntity userEntity) {
        Claims claims = Jwts.claims().setSubject(userEntity.getUser());
        claims.put("rol", "admin");

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String token = Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact();
        return token;
    }

    /**
     * Allows to validate token.
     * @param token value.
     * @return true or false.
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey.getBytes(Charset.forName("UTF-8")))
                    .parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("Expired or invalid JWT");
        }
    }
}
