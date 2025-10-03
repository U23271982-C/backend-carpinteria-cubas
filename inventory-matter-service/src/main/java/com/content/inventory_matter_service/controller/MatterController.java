package com.content.inventory_matter_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matter")
public class MatterController {
    @RequestMapping
    public String getMatter() {
        return "Se corroboro que funicine matter";
    }
}
