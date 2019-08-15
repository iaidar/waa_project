package com.project.onlinestore.review.service;

import com.project.onlinestore.review.domain.Review;

import java.util.List;

public interface ReviewService {
    Review save(Review review);
    List<Review> getApprovedReviewsByProduct(Long productId);
}
