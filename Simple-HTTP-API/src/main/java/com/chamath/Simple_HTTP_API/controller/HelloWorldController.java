package com.chamath.Simple_HTTP_API.controller;


import com.chamath.Simple_HTTP_API.dto.MessageResponse;
import com.chamath.Simple_HTTP_API.exception.InvalidInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private static final char MID_ALPHABET = 'm';

    @GetMapping("/hello-world")
    public ResponseEntity<MessageResponse> helloWorld(
            @RequestParam(required = false) String name) {

        if (name == null || name.isBlank()) {
            throw new InvalidInputException();
        }

        char firstChar = Character.toLowerCase(name.trim().charAt(0));

        if (!Character.isLetter(firstChar)) {
            throw new InvalidInputException();
        }

        if (firstChar > MID_ALPHABET) {
            throw new InvalidInputException();
        }

        String capitalized = capitalize(name.trim());
        return ResponseEntity.ok(new MessageResponse("Hello " + capitalized));
    }

    private String capitalize(String value) {
        String lower = value.toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
}
