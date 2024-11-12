package com.example.coursesystem.entity.cart;

import com.example.coursesystem.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "shopping_carts")
@NoArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private double totalPrice;

    public void addItem(CartItem item) {
        items.add(item);
        item.setShoppingCart(this);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
    }

    public void clearCart(){
        items.clear();
    }

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
