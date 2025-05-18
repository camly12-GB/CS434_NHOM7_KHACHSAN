package com.example.thangcachep.demo.service;

import com.example.thangcachep.demo.exception.MyException;
import com.example.thangcachep.demo.model.dto.PasswordDTO;
import com.example.thangcachep.demo.model.dto.UserDTO;

public interface IUserService {

//    UserDTO findOneByUserNameAndStatus(String name, int status);
//    List<UserDTO> getUsers(String searchValue, Pageable pageable);
//    int getTotalItems(String searchValue);
//    UserDTO findOneByUserName(String userName);
    UserDTO findUserById(long id);

    UserDTO insert(UserDTO userDTO);

    void createUser(UserDTO userDTO);

    UserDTO update(Long id, UserDTO userDTO);

    void updatePassword(long id, PasswordDTO userDTO) throws MyException;

 UserDTO findByUserNameAndStatus(String name, Long status);

    UserDTO findByUserName(UserDTO userDTO);

    UserDTO resetPassword(long id);

//    UserDTO updateProfileOfUser(String id, UserDTO userDTO);

    void delete(long[] ids);

//    Map<Long, String> listStaff();
//    //    ResponseDTO listStaff(Long buildingId);
//    List<UserDTO> getAllUsers(Pageable pageable);
//    int countTotalItems();
}
