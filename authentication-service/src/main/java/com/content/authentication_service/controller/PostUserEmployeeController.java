package com.content.authentication_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post-user-employee")
public class PostUserEmployeeController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getBien() {
        return "Hola, prueba se dió correctamente post user employee";
    }
}
