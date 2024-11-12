package com.example.coursesystem.dto.cart;

import com.example.coursesystem.entity.cart.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;

public record ShoppingCartDTO(Long id, Long userId, List<CartItemDTO> items, BigDecimal total) {
  public ShoppingCartDTO(ShoppingCart cart) {
    this(
        cart.getId(),
        cart.getUser().getId(),
        cart.getItems().stream().map(CartItemDTO::new).toList(),
        cart.calculateTotal());
  }
}
