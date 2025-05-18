package com.example.thangcachep.demo.repository;

import com.example.thangcachep.demo.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByDescribe(String code);

}
