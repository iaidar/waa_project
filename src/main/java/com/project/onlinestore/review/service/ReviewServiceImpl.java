package com.project.onlinestore.review.service;

import com.project.onlinestore.product.domain.Product;
import com.project.onlinestore.product.service.ProductService;
import com.project.onlinestore.review.domain.Review;
import com.project.onlinestore.review.repository.ReviewRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRespository reviewRespository;

    @Autowired
    ProductService productService;

    @Override
    public Review save(Review review) {
        return reviewRespository.save(review);
    }

    @Override
    public List<Review> getApprovedReviewsByProduct(Long productId) {
        return reviewRespository.findByProductAndStatusEqualsOrderByLocalDateTimeDesc(productService.findById(productId),2);
    }
}
