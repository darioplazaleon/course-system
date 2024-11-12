package com.example.coursesystem.dto.cart;

import com.example.coursesystem.entity.cart.CartItem;

import java.math.BigDecimal;

public record CartItemDTO(
        Long id,
        Long courseId,
        String courseTitle,
        BigDecimal totalPrice
) {
    public CartItemDTO(CartItem item) {
        this(item.getId(), item.getCourse().getId(), item.getCourse().getTitle(), item.getPrice());
    }
}
