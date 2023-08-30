package com.works.configs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid( MethodArgumentNotValidException exception ){
        Map<String, Object> hm = new LinkedHashMap<>();
        Map<String, Object> hm2 = new LinkedHashMap<>();
        List<Object> errors = new ArrayList<>();
        for (int i=0;i<exception.getFieldErrors().size();i++){
            hm2.put("field",exception.getFieldErrors().get(i).getField());
            hm2.put("defaultmessage",exception.getFieldErrors().get(i).getDefaultMessage());
            hm2.put("code",exception.getFieldErrors().get(i).getCodes());
        }
        errors.add(hm2);
        hm.put("status",false);
        hm.put("result",errors);
        return new ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }

}
