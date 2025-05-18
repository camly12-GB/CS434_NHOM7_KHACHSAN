package com.example.thangcachep.demo.repository.custom;

import com.example.thangcachep.demo.entity.UserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserEntity> findByRole(String roleCode);
    List<UserEntity> getAllUsers(Pageable pageable);
    int countTotalItem();
}
