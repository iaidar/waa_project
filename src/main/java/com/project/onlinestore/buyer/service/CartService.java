package com.project.onlinestore.buyer.service;

import com.project.onlinestore.buyer.domain.Cart;

public interface CartService {
    public Cart addToCart(Long productId, String username, int quantity);
}
