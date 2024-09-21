package br.com.music.app.musicapp.api.config.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.TimeoutException;

@RestControllerAdvice
public class ApiControllerException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity exception404(){
        return ResponseEntity.status(404).body("No response content return");
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity exceptionNull(){
        return ResponseEntity.badRequest().body("Null response return");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity errorNull(){
        return ResponseEntity.status(500).body("Error on execute search");
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity timeOutError(){return ResponseEntity.status(408).body("Timeout in execute service");}


}
