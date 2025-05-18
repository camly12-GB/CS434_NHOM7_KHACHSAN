package com.example.thangcachep.demo.repository;

import com.example.thangcachep.demo.entity.UserEntity;
import com.example.thangcachep.demo.model.dto.UserDTO;
import com.example.thangcachep.demo.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
//    Page<UserEntity> findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status,
//                                                                                                  Pageable pageable);
//    List<UserEntity> findByStatusAndRoles_Code(Integer status, String roleCode);
//    Page<UserEntity> findByStatusNot(int status, Pageable pageable);
//    long countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status);
//    long countByStatusNot(int status);
//    UserEntity findOneByUserName(String userName);
//    List<UserEntity> findByIdIn(List<Long> id);
List<UserEntity> findByFullNameAndUserName(String fullName, String userName);
      UserEntity findByUserNameAndStatus(String userName, Long status);
      UserEntity findByUserName(String userName);
}
