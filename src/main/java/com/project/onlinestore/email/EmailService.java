package com.project.onlinestore.email;

import com.project.onlinestore.security.domain.User;

public interface EmailService {
    void sendPendingAcceptanceEmail(User seller);
    void sendPendingRejectEmail(User seller);
}
