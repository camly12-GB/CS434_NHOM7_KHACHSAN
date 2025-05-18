package com.example.thangcachep.demo.service.impl;

import com.example.thangcachep.demo.model.dto.MyUserDetail;
import com.example.thangcachep.demo.model.dto.RoleDTO;
import com.example.thangcachep.demo.model.dto.UserDTO;
import com.example.thangcachep.demo.service.IUserService;
import com.mysql.cj.exceptions.PasswordExpiredException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService userService;

//    @Override
    public UserDetails loadUserByUsername(UserDTO userDTOLogin) throws UsernameNotFoundException { //truyen vao name roi lay ra nguoi do
        UserDTO userDTO = userService.findByUserName(userDTOLogin);

        System.out.println("userDTOLogin: " + userDTOLogin.getUserName()); // Kiểm tra đầu vào
        System.out.println("userDTO: " + userDTO); // Kiểm tra kết quả tìm kiếm

        if(userDTO == null){
            throw new UsernameNotFoundException("Username not found");
        }
//        if(userDTO.getPassword() != userDTOLogin.getPassword()){
//            throw new UsernameNotFoundException("Username or password is wrong");
//        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(userDTOLogin.getPassword(), userDTO.getPassword())) {
            throw new UsernameNotFoundException("password is wrong");
        }
            //phan quyen
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(RoleDTO role: userDTO.getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getDescribe()));
        }

        MyUserDetail myUserDetail = new MyUserDetail(userDTO.getUserName(),userDTO.getPassword(),true,true,true,true,authorities);

        //copy userDTO qua myUserDetail nếu khớp thì username và passwowrd đúng, sai trả về lỗi
        BeanUtils.copyProperties(userDTO, myUserDetail);
        return myUserDetail;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}