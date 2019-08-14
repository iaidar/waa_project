package com.project.onlinestore.buyer.service;

import com.project.onlinestore.buyer.domain.Order;

public interface OrderService {
    Order saveOrder(Order order,Long cartId,String username);
    Order getOrderById(Long id);
}
