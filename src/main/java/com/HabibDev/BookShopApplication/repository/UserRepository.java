package com.HabibDev.BookShopApplication.repository;

import com.HabibDev.BookShopApplication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findByEmail(String email);

    boolean existsByEmail(String email);
}
