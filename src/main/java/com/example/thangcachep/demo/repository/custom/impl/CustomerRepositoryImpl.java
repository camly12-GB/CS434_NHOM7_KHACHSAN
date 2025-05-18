package com.example.thangcachep.demo.repository.custom.impl;

import com.example.thangcachep.demo.entity.CustomerEntity;
import com.example.thangcachep.demo.repository.CustomerRepository;
import com.example.thangcachep.demo.repository.custom.CustomerRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom  {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public CustomerEntity findByName(String customerId) {
        return null;
    }
}
