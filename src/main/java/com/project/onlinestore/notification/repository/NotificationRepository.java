package com.project.onlinestore.notification.repository;

import com.project.onlinestore.notification.domain.Notification;
import com.project.onlinestore.security.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification,Long> {
    int countAllByUserAndSeenFalse(User user);
    List<Notification> findByUserAndSeenFalseOrderByLocalDateTime(User user);
    List<Notification> findByUserOrderByLocalDateTime(User user);
}
