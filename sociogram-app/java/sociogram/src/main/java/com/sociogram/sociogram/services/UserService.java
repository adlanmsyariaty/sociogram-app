package com.sociogram.sociogram.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sociogram.sociogram.helpers.Jwt;
import com.sociogram.sociogram.helpers.ResponseHandler;
import com.sociogram.sociogram.models.User;
import com.sociogram.sociogram.repositories.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private Jwt jwt;

  @Autowired
  private BCryptPasswordEncoder encoder;

  class AccessToken {
    public String accessToken;
  }

  public ResponseEntity<Object> login(User user) {
    try {
      if (user.getEmail() == null) {
        throw new Exception("EMAIL_IS_REQUIRED");
      }
      if (user.getPassword() == null) {
        throw new Exception("PASSWORD_IS_REQUIRED");
      }

      User userData = userRepository.findUserByEmail(user.getEmail());

      if (userData == null) {
        throw new Exception("USER_NOT_FOUND");
      } else {
        Boolean isPasswordValid = encoder.matches(user.getPassword(), userData.getPassword());

        if (!isPasswordValid) {
          throw new Exception("INVALID_PASSWORD");
        }

        AccessToken res = new AccessToken();
        res.accessToken = jwt.generateAccessToken(userData);

        return ResponseHandler.generateResponse("Success to login", HttpStatus.OK, res);
      }
    } catch (Exception e) {
      if (e.getMessage() == "USER_NOT_FOUND") {
        return ResponseHandler.generateResponse("User not found", HttpStatus.NOT_FOUND, null);
      } else if (e.getMessage() == "EMAIL_IS_REQUIRED") {
        return ResponseHandler.generateResponse("Email is required", HttpStatus.BAD_REQUEST, null);
      } else if (e.getMessage() == "PASSWORD_IS_REQUIRED") {
        return ResponseHandler.generateResponse("Password is required", HttpStatus.BAD_REQUEST, null);
      } else if (e.getMessage() == "INVALID_PASSWORD") {
        return ResponseHandler.generateResponse("Email/Password is invalid", HttpStatus.BAD_REQUEST, null);
      } else {
        return ResponseHandler.generateResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR, null);
      }
    }
  }

  public ResponseEntity<Object> signup(User newUser) {
    try {
      if (newUser.getEmail() == null) {
        throw new Exception("EMAIL_IS_REQUIRED");
      }
      if (newUser.getPassword() == null) {
        throw new Exception("PASSWORD_IS_REQUIRED");
      }

      newUser.setPassword(encoder.encode(newUser.getPassword()));
      userRepository.save(newUser);

      return ResponseHandler.generateResponse("Success to create new user", HttpStatus.OK, null);

    } catch (Exception e) {
      if (e.getMessage() == "USER_NOT_FOUND") {
        return ResponseHandler.generateResponse("User not found", HttpStatus.NOT_FOUND, null);
      } else if (e.getMessage() == "EMAIL_IS_REQUIRED") {
        return ResponseHandler.generateResponse("Email is required", HttpStatus.BAD_REQUEST, null);
      } else if (e.getMessage() == "PASSWORD_IS_REQUIRED") {
        return ResponseHandler.generateResponse("Password is required", HttpStatus.BAD_REQUEST, null);
      } else {
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
      }

    }
  }

}
