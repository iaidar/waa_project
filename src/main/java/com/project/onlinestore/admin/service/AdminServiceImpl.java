package com.project.onlinestore.admin.service;

import com.project.onlinestore.email.EmailService;
import com.project.onlinestore.security.domain.User;
import com.project.onlinestore.security.respository.RoleRepository;
import com.project.onlinestore.security.respository.UserRepository;
import com.project.onlinestore.utils.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService  {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmailService emailService;

    @Override
    public List<User> getPendingSellers() {
        return userRepository.findByActiveAndRoleId(0,SecurityConstants.ROLE_SELLER);
    }

    public void acceptSeller(Long sellerId){
        User seller = userRepository.findById(sellerId).get();
        seller.setActive(1);
        userRepository.save(seller);
        emailService.sendPendingAcceptanceEmail(seller);
    }

    public void rejectSeller(Long sellerId){
        User seller = userRepository.findById(sellerId).get();
        userRepository.delete(seller);
        emailService.sendPendingRejectEmail(seller);
    }
}
