package com.sourav78.CodeQuizBackend.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

// Response Handler class
public class ResponseHandler {

    // Response Builder method
    public static ResponseEntity<Object> responseBuilder(
            String message,
            HttpStatus httpStatus,
            Object responseObject
    ){

        // Create response map
        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("success", true);
        responseMap.put("message", message);
        responseMap.put("httpStatus", httpStatus);

        if(responseObject != null){
            responseMap.put("data", responseObject);
        }

        // Return response entity
        return new ResponseEntity<>(responseMap, httpStatus);
    }

}
