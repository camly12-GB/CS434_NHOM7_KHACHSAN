package com.example.thangcachep.demo.repository.custom;

import com.example.thangcachep.demo.entity.CustomerEntity;

public interface CustomerRepositoryCustom {

    CustomerEntity findByName(String customerId);

}
