package com.example.thangcachep.demo.repository;

import com.example.thangcachep.demo.entity.CustomerEntity;
import com.example.thangcachep.demo.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long>, CustomerRepositoryCustom {


}
