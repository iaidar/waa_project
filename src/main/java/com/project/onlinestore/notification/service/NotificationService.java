package com.project.onlinestore.notification.service;

import com.project.onlinestore.notification.domain.Notification;
import com.project.onlinestore.security.domain.User;
import com.project.onlinestore.seller.domain.Seller;

import java.util.List;

public interface NotificationService {

    Notification save(User user,String message,String link);
    int countUnseenNotifications(String username);
    List<Notification> getUnseenNotifications(String username);
    void notifyAllFollowers(Seller seller,String message,String link);
    void makeAllNotificationsSeenByUser(String username);
    void notifyOrderBuyer(Long orderId);
    void notifyOrderAdmin(Long orderId);
    void notifyOrderSeller(Long orderId);
}
