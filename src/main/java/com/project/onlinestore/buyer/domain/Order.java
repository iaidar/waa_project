package com.project.onlinestore.buyer.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Valid
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Valid
    private Address billingAddress;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @Valid
    private Payment payment;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Buyer buyer;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Line> lines;

    private int status=1;

}