package com.project.onlinestore.buyer.service;

import com.project.onlinestore.buyer.domain.Buyer;
import com.project.onlinestore.buyer.domain.Cart;
import com.project.onlinestore.buyer.domain.Line;
import com.project.onlinestore.buyer.repository.CartRepository;
import com.project.onlinestore.product.domain.Product;
import com.project.onlinestore.product.service.ProductService;
import com.project.onlinestore.security.domain.User;
import com.project.onlinestore.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository repository;

    @Autowired
    private BuyerService buyerService;

    public Cart addToCart(Long productId, String username, int quantity) {
        Line line = new Line();
        line.setQty(quantity);
        line.setProduct(productService.findById(productId));

        Cart cart= buyerService.getBuyerByUsername(username).getCart();
        if (cart==null)
            cart = new Cart();
        cart.addLine(line);

        return repository.save(cart);
    }
}
