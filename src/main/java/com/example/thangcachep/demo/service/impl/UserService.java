package com.example.thangcachep.demo.service.impl;

import com.example.thangcachep.demo.constant.SystemConstant;
import com.example.thangcachep.demo.converter.UserConverter;
import com.example.thangcachep.demo.entity.RoleEntity;
import com.example.thangcachep.demo.entity.UserEntity;
import com.example.thangcachep.demo.exception.MyException;
import com.example.thangcachep.demo.model.dto.PasswordDTO;
import com.example.thangcachep.demo.model.dto.UserDTO;
import com.example.thangcachep.demo.repository.RoleRepository;
import com.example.thangcachep.demo.repository.UserRepository;
import com.example.thangcachep.demo.service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDTO findUserById(long id) {
        UserEntity entity = userRepository.findById(id).get();
        List<RoleEntity> roles = entity.getRoles();
        UserDTO dto = userConverter.convertToDto(entity);
        roles.forEach(item -> {
            dto.setRoleCode(item.getDescribe());
        });
        return dto;
    }

    @Override
    public UserDTO insert(UserDTO userDTO) {
        RoleEntity role = roleRepository.findByDescribe(userDTO.getRoleCode());
        UserEntity userEntity = userConverter.convertToEntity(userDTO);
        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
        userEntity.setStatus(1L);
        userEntity.setPassword(passwordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));
        return userConverter.convertToDto(userRepository.save(userEntity));
    }

    @Override
    public void createUser(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertToEntity(userDTO);
        userEntity.setStatus(1L);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
//        userEntity.setPassword(userEntity.getPassword());
        userRepository.save(userEntity);
    }

    @Override
    public UserDTO update(Long id, UserDTO updateUser) {
        RoleEntity role = roleRepository.findByDescribe(updateUser.getRoleCode());
        UserEntity oldUser = userRepository.findById(id).get();
        UserEntity userEntity = userConverter.convertToEntity(updateUser);
        userEntity.setUserName(oldUser.getUserName());
        userEntity.setStatus(oldUser.getStatus());
        userEntity.setRoles(Stream.of(role).collect(Collectors.toList()));
        userEntity.setPassword(oldUser.getPassword());
        return userConverter.convertToDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public void updatePassword(long id, PasswordDTO passwordDTO) throws MyException {
        UserEntity user = userRepository.findById(id).get();
        if (passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())
                && passwordDTO.getNewPassword().equals(passwordDTO.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new MyException(SystemConstant.CHANGE_PASSWORD_FAIL);
        }
    }

    @Override
    public UserDTO findByUserName(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findByUserName(userDTO.getUserName());
        UserDTO userDTOLogin = userConverter.convertToDto(userEntity);
        return userDTOLogin;
    }

    @Override
    public UserDTO findByUserNameAndStatus(String name, Long status) {
        UserEntity userEntity = userRepository.findByUserNameAndStatus(name,status);
        UserDTO userDTO = userConverter.convertToDto(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO resetPassword(long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setPassword(passwordEncoder.encode(SystemConstant.PASSWORD_DEFAULT));
        return userConverter.convertToDto(userRepository.save(userEntity));
    }

    @Override
    public void delete(long[] ids) {
        for (Long item : ids) {
            UserEntity userEntity = userRepository.findById(item).get();
            userEntity.setStatus(0L);
            userRepository.save(userEntity);
        }
    }
}
