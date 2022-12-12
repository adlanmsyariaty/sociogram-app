package com.sociogram.sociogram.securities;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sociogram.sociogram.models.User;
import com.sociogram.sociogram.repositories.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthzFilter extends OncePerRequestFilter {
  private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";

  @Autowired
  private UserRepository userRepository;

  @Value("${secret}")
  private String secretKey;

  @Override
  protected void doFilterInternal(
    HttpServletRequest req,
    HttpServletResponse res,
    FilterChain chain
  ) throws IOException, ServletException {
    try {
      if (checkJWTToken(req, res)) {
        String access_token = req.getHeader(HEADER).replace(PREFIX, "");
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(access_token).getBody();

        String userEmail = claims.get("email").toString();
        User userData = userRepository.findUserByEmail(userEmail);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userData.getName(), null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(auth);
      } else {
        SecurityContextHolder.clearContext();
      }
      chain.doFilter(req, res);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
      System.out.println(e.getMessage());
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) res).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
  }

  private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
		String authenticationHeader = request.getHeader(HEADER);
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
			return false;
		return true;
	}
}
