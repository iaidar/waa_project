package com.project.onlinestore.seller.domain;

import com.project.onlinestore.product.domain.Product;
import com.project.onlinestore.security.domain.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "seller")
    private List<Product> products;

}
