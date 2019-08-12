package com.project.onlinestore.buyer.service;

import com.project.onlinestore.buyer.domain.Buyer;

public interface BuyerService {
    Buyer save(Buyer buyer);
    Boolean follow(Buyer buyer,Long sellerId);
    Boolean unfollow(Buyer buyer,Long sellerId);
}
