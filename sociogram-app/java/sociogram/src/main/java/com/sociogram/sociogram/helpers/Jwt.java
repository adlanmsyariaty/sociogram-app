package com.sociogram.sociogram.helpers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sociogram.sociogram.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Jwt {

  @Value("${secret}")
  private String secretKey;

  public static final long TOKEN_VALIDITY = 10 * 60 * 60;

  public String generateAccessToken(User userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", userDetails.getId());
    claims.put("email", userDetails.getEmail());
    return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
      .signWith(SignatureAlgorithm.HS512, secretKey).compact();
  }
}
