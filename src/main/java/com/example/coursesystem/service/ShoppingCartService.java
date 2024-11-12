package com.example.coursesystem.service;

import com.example.coursesystem.dto.cart.ShoppingCartDTO;
import com.example.coursesystem.entity.cart.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCartDTO getShoppingCart(Long userId);
    ShoppingCartDTO addCourseToShoppingCart(Long userId, Long courseId);
    ShoppingCartDTO removeCourseFromShoppingCart(Long userId, Long courseId);
    ShoppingCart createCartForUser(Long userId);

}
