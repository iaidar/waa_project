package com.project.onlinestore.buyer.repository;

import com.project.onlinestore.buyer.domain.Buyer;
import com.project.onlinestore.buyer.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {
    List<Order> findAllByBuyer(Buyer buyer);
        List<Order> findAllByBuyerAndStatusEquals(Buyer buyer, int status);
}
