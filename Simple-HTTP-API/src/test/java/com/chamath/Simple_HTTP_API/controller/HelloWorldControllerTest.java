package com.chamath.Simple_HTTP_API.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnGreeting_whenValidName() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("message").value("Hello Alice"));
    }

    @Test
    void shouldThrowException_whenNameIsMissing() throws Exception {
        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldThrowException_whenNameIsBlank() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "   "))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldThrowException_whenFirstCharNotLetter() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "123abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldThrowException_whenFirstCharAfterM() throws Exception {
        mockMvc.perform(get("/hello-world")
                        .param("name", "zack"))
                .andExpect(status().isBadRequest());
    }
}
