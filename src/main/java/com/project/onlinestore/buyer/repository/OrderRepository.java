package com.project.onlinestore.buyer.repository;

import com.project.onlinestore.buyer.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {
}
