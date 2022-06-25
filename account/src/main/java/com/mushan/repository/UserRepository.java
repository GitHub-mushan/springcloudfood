package com.mushan.repository;

import com.mushan.entity.Admin;
import com.mushan.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    public User login(String username, String password);
}
