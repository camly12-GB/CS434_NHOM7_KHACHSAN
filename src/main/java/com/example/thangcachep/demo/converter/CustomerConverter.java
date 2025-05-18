package com.example.thangcachep.demo.converter;

import com.example.thangcachep.demo.constant.SystemConstant;
import com.example.thangcachep.demo.entity.CustomerEntity;
import com.example.thangcachep.demo.entity.RoleEntity;
import com.example.thangcachep.demo.entity.UserEntity;
import com.example.thangcachep.demo.model.dto.CustomerDTO;
import com.example.thangcachep.demo.repository.CustomerRepository;
import com.example.thangcachep.demo.repository.RoleRepository;
import com.example.thangcachep.demo.repository.UserRepository;
import com.example.thangcachep.demo.security.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerConverter {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public CustomerDTO convertToCustomerDTO(CustomerEntity customerEntity){
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        return customerDTO;
    }

    public CustomerEntity convertToCustomerEntity(CustomerDTO customerDTO){
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
//        customerEntity.setStatus(1L);
        return customerEntity;
    }

    public CustomerEntity setContact(CustomerDTO customerDTO){
        CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("contact");
        userEntity.setFullName("anonymous");
        userEntity.setPassword(bCryptPasswordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));
        userEntity.setStatus(1L);
        userEntity.setEmail("anonymous@gmail.com");

        List<RoleEntity> roleEntities = new ArrayList<>();
        RoleEntity roleEntity = roleRepository.findByDescribe("Customer");
        roleEntities.add(roleEntity);
        userEntity.setRoles(roleEntities);
        userRepository.save(userEntity);

        List<UserEntity> userEntities = userRepository.findByFullNameAndUserName("anonymous","contact");
        customerEntity.setUserEntities(userEntities);

        customerEntity.setStatus(1L);
        if(SecurityUtils.getAuthorities().contains(SystemConstant.MANAGER_ROLE) || SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)){
            customerEntity.setCreatedBy((SecurityUtils.getPrincipal().getUsername()));
        }else {
            customerEntity.setCreatedBy("anonymousUser");
        }

        //48->62 test
         return customerEntity;
    }
}
