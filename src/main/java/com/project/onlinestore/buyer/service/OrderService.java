package com.project.onlinestore.buyer.service;

import com.project.onlinestore.buyer.domain.Buyer;
import com.project.onlinestore.buyer.domain.Order;

import java.util.List;

public interface OrderService {
    Order saveOrder(Order order,Long cartId,String username);
    Order getOrderById(Long id);
    List<Order> findAllByBuyer(Buyer buyer);
    void save(Order order);
}
