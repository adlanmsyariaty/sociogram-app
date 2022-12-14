package com.sociogram.sociogram.helpers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
  public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
    Map<String, Object> map = generateHashMap(message, status, responseObj);
    
    return new ResponseEntity<Object>(map, status);
  }

  public static Map<String, Object> generateHashMap(String message, HttpStatus status, Object responseObj) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("message", message);
    map.put("statusCode", status.value());
    map.put("data", responseObj);

    return map;
  }
}
