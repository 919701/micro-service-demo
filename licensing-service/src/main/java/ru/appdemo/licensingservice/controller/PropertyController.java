package ru.appdemo.licensingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @GetMapping
    public String getPropertyValue() {
        return "Property value + " + LocalDateTime.now();
    }
}
