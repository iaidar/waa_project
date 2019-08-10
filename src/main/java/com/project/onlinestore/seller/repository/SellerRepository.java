package com.project.onlinestore.seller.repository;

import com.project.onlinestore.security.domain.User;
import com.project.onlinestore.seller.domain.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<Seller, Long> {
    @Query("select s from Seller s where s.user =?1")
    Seller getSellerByUser(User user);
}
