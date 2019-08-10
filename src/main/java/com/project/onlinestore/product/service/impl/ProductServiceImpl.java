package com.project.onlinestore.product.service.impl;

import com.project.onlinestore.product.domain.Product;
import com.project.onlinestore.product.repository.ProductRepository;
import com.project.onlinestore.product.service.ProductService;
import com.project.onlinestore.seller.domain.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;


    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findProductsBySeller(Seller seller) {
        return productRepository.findProductsBySeller(seller);
    }

}
