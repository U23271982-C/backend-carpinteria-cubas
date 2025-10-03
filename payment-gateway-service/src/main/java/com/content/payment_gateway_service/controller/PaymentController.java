package com.content.payment_gateway_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @RequestMapping
    public String getPayment() {
        return "Se corroboro que funicine payment";
    }
}
