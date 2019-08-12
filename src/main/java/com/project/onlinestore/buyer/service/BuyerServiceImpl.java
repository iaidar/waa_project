package com.project.onlinestore.buyer.service;

import com.project.onlinestore.buyer.domain.Buyer;
import com.project.onlinestore.buyer.repository.BuyerReposiotry;
import com.project.onlinestore.seller.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    BuyerReposiotry buyerReposiotry;

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public Buyer save(Buyer buyer) {
        return buyerReposiotry.save(buyer);
    }

    @Override
    public Boolean follow(Buyer buyer, Long sellerId) {
        if (buyer.getFollowing()==null)
            buyer.setFollowing(new ArrayList<>());
        buyer.getFollowing().add(sellerRepository.findById(sellerId).get());
        return true;
    }

    @Override
    public Boolean unfollow(Buyer buyer, Long sellerId) {
        if (buyer.getFollowing()==null)
            buyer.setFollowing(new ArrayList<>());
        buyer.getFollowing().remove(sellerRepository.findById(sellerId).get());
        return true;
    }


}
