package com.project.onlinestore.security.respository;

import com.project.onlinestore.security.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    @Query("select u from User u where u.username = ?1")
    User findByUsername(String username);
}
