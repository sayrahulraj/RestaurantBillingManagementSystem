package com.RestaurantBillingManagementSystem.services;

import com.RestaurantBillingManagementSystem.model.UserReview;

import java.util.List;

public interface UserReviewService {
    List<UserReview> getUserReview();

    UserReview createUserReview(UserReview userReview);

    UserReview getUserReviewById(int id);

    UserReview updateUserReview(UserReview userReview);

    UserReview deleteUserReviewById(int id);
}
