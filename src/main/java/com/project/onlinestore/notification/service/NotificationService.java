package com.project.onlinestore.notification.service;

import com.project.onlinestore.notification.domain.Notification;
import com.project.onlinestore.security.domain.User;

import java.util.List;

public interface NotificationService {

    Notification save(User user,String message);
    void seen(Notification notification);
    int countUnseenNotifications(User user);
    List<Notification> getUnseenNotifications(User user);
}
