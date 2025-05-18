package com.example.thangcachep.demo.service.impl;

import com.example.thangcachep.demo.converter.CustomerConverter;
import com.example.thangcachep.demo.entity.CustomerEntity;
import com.example.thangcachep.demo.model.dto.CustomerDTO;
import com.example.thangcachep.demo.repository.CustomerRepository;
import com.example.thangcachep.demo.service.ICustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerConverter customerConverter;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void addCustomerContact(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.setContact(customerDTO);
        customerRepository.save(customerEntity);
    }
}
