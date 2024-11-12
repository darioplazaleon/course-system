package com.example.coursesystem.controller;

import com.example.coursesystem.dto.cart.ShoppingCartDTO;
import com.example.coursesystem.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shoppingCart")
@RequiredArgsConstructor
public class ShoppingCartController {
  private final ShoppingCartService shoppingCartService;

  @GetMapping("/{userId}")
  public ResponseEntity<ShoppingCartDTO> getCart(@PathVariable Long userId) {
    return ResponseEntity.ok(shoppingCartService.getShoppingCart(userId));
  }

  @PostMapping("/{userId}/add")
  public ResponseEntity<ShoppingCartDTO> addCourse(
      @PathVariable Long userId, @RequestParam Long courseId) {
    return ResponseEntity.ok(shoppingCartService.addCourseToShoppingCart(userId, courseId));
  }

  @DeleteMapping("/{userId}/remove")
  public ResponseEntity<ShoppingCartDTO> removeCourse(
      @PathVariable Long userId, @RequestParam Long courseId) {
    return ResponseEntity.ok(shoppingCartService.removeCourseFromShoppingCart(userId, courseId));
  }
}
