package com.mushan.repository;

import com.mushan.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository {
    public Admin login(String username,String password);

}
