package com.project.onlinestore.notification.service;

import com.project.onlinestore.notification.domain.Notification;
import com.project.onlinestore.notification.repository.NotificationRepository;
import com.project.onlinestore.security.domain.User;
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
    public int countUnseenNotifications(User user) {
        return notificationRepository.countAllByUserAndSeenFalse(user);
    }

    @Override
    public List<Notification> getUnseenNotifications(User user) {
        return notificationRepository.findByUserAndSeenFalseOrderByLocalDateTime(user);
    }
}
