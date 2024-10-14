package com.api.stockman.utilities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class ApiResponse {

    public static ResponseEntity<Map<String, Object>> success(Object data, String message){
        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("message", message);
        response.put("status", HttpStatus.OK.value());

        return ResponseEntity.ok(response);
    }

    public static ResponseEntity<Map<String, Object>> failed(Object data, String message){
        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("message", message);
        response.put("status", HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.ok(response);
    }
}
