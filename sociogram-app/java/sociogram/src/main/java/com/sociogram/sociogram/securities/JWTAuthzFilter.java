package com.sociogram.sociogram.securities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sociogram.sociogram.helpers.ResponseHandler;
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

  @Autowired
  ObjectMapper mapper;

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
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);

      ServletServerHttpResponse response = new ServletServerHttpResponse(res);
      response.setStatusCode(HttpStatus.UNAUTHORIZED);
      response.getServletResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

      Map<String, Object> resp = ResponseHandler.generateHashMap(e.getMessage(), HttpStatus.UNAUTHORIZED, null);

      response.getBody().write(mapper.writeValueAsString(resp).getBytes());
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
