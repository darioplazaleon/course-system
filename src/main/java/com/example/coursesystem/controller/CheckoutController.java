package com.example.coursesystem.controller;

import com.example.coursesystem.service.impl.ICheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final ICheckoutService checkoutService;

    @PostMapping("/{userId}")
    public ResponseEntity<Void> checkout(@PathVariable Long userId) {
        checkoutService.checkout(userId);
        return ResponseEntity.ok().build();
    }
}
