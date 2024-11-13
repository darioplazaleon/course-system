package com.example.coursesystem.service.impl;

import com.example.coursesystem.entity.Enrollment;
import com.example.coursesystem.entity.cart.CartItem;
import com.example.coursesystem.entity.cart.ShoppingCart;
import com.example.coursesystem.repository.EnrollmentRepository;
import com.example.coursesystem.repository.ShoppingCartRepository;
import com.example.coursesystem.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ICheckoutService implements CheckoutService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public void checkout(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId);

        if (cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        for (CartItem cartItem : cart.getItems()) {
            Enrollment enrollment = new Enrollment(cart.getUser(), cartItem.getCourse());
            enrollmentRepository.save(enrollment);
        }

        cart.getItems().clear();
        shoppingCartRepository.save(cart);

    }
}
