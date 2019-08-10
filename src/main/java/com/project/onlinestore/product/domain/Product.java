package com.project.onlinestore.product.domain;

import com.project.onlinestore.security.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 2, max = 50)
    private String title;
    private String description;
    private Double price;

    @Column(columnDefinition = "true")
    private Boolean active;

    @ManyToOne
    private User seller;
}
