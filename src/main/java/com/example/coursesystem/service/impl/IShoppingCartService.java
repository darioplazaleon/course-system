package com.example.coursesystem.service.impl;

import com.example.coursesystem.dto.cart.ShoppingCartDTO;
import com.example.coursesystem.entity.*;
import com.example.coursesystem.entity.cart.CartItem;
import com.example.coursesystem.entity.cart.ShoppingCart;
import com.example.coursesystem.repository.*;
import com.example.coursesystem.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IShoppingCartService implements ShoppingCartService {

  private final ShoppingCartRepository shoppingCartRepository;
  private final UserRepository userRepository;
  private final EnrollmentRepository enrollmentRepository;
  private final CartItemRepository cartItemRepository;
  private final CourseRepository courseRepository;

  @Override
  public ShoppingCartDTO getShoppingCart(Long userId) {
    ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
    return new ShoppingCartDTO(cart);
  }

  @Override
  public ShoppingCartDTO addCourseToShoppingCart(Long userId, Long courseId) {
    ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
    if (cart == null) {
      createCartForUser(userId);
    }

    Course course =
        courseRepository
            .findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found"));

    CartItem item =
        cart.getItems().stream()
            .filter(cartItem -> cartItem.getCourse().getId().equals(courseId))
            .findFirst()
            .orElseGet(
                () -> {
                  CartItem newItem = new CartItem();
                  newItem.setCourse(course);
                  newItem.setPrice(course.getPrice());
                  cart.addItem(newItem);
                  return newItem;
                });

    cartItemRepository.save(item);
    shoppingCartRepository.save(cart);

    return new ShoppingCartDTO(cart);
  }

  @Override
  public ShoppingCartDTO removeCourseFromShoppingCart(Long userId, Long courseId) {
    ShoppingCart cart = shoppingCartRepository.findByUserId(userId);
    if (cart == null) {
      throw new RuntimeException("Cart not found");
    }

    CartItem item =
        cart.getItems().stream()
            .filter(cartItem -> cartItem.getCourse().getId().equals(courseId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Course not found in cart"));

    cart.removeItem(item);
    cartItemRepository.delete(item);
    shoppingCartRepository.save(cart);

    return new ShoppingCartDTO(cart);
  }

  @Override
  public ShoppingCart createCartForUser(Long userId) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    ShoppingCart newCart = new ShoppingCart();
    newCart.setUser(user);
    return shoppingCartRepository.save(newCart);
  }
}
