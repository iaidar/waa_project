package com.project.onlinestore.notification.service;

import com.project.onlinestore.buyer.domain.Buyer;
import com.project.onlinestore.notification.domain.Notification;
import com.project.onlinestore.notification.repository.NotificationRepository;
import com.project.onlinestore.security.domain.User;
import com.project.onlinestore.security.service.UserService;
import com.project.onlinestore.seller.domain.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserService userService;

    @Override
    public Notification save(User user,String message) {
        Notification notification = new Notification();
        notification.setSeen(false);
        notification.setLocalDateTime(LocalDateTime.now());
        notification.setMessage(message);
        notification.setUser(user);
        return notificationRepository.save(notification);
    }

    @Override
    public void seen(Notification notification) {
        notification.setSeen(true);
        notificationRepository.save(notification);
    }

    @Override
    public int countUnseenNotifications(String username) {
        return notificationRepository.countAllByUserAndSeenFalse(userService.findUserByUserName(username));
    }

    @Override
    public List<Notification> getUnseenNotifications(String username) {
        return notificationRepository.findByUserAndSeenFalseOrderByLocalDateTime(userService.findUserByUserName(username));
    }

    @Override
    public void notifyAllFollowers(Seller seller, String message) {
        List<Buyer> buyers = seller.getFollowers();
        for (Buyer buyer:buyers){
            User user = buyer.getUser();
            save(user,message);
        }
    }

}
