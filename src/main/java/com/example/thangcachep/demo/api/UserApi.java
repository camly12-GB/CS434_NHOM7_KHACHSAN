package com.example.thangcachep.demo.api;

import com.example.thangcachep.demo.service.IUserService;
import com.example.thangcachep.demo.service.impl.CustomUserDetailService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import com.example.thangcachep.demo.model.dto.ResponseDTO;
import com.example.thangcachep.demo.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
@Slf4j

public class UserApi {

    @Autowired
    private IUserService userService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping(value = "/user/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        log.info("Creating users with {}", userDTO.getUserName());
        try{
            if(bindingResult.hasErrors()){
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setMessage("Failed");
                responseDTO.setDetail(errorMessages);
                return ResponseEntity.badRequest().body(responseDTO);
            }
            userService.createUser(userDTO);
            return  ResponseEntity.ok("Sign up successfully!");
//            return ResponseEntity.ok(userService.signUpUser(userDTO));
        }catch (Exception ex){
            log.error("Error creating users", ex);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping(value = "/user")
    public void getUser(@RequestParam(required = false) String userName, @RequestParam(required = false) String password){
        System.out.println("oke luon");
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable String id){
        return "success";
    }

    @PostMapping(value = "/user/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){
        log.info("Login user with {}", userDTO.getUserName());
        try{
            if(bindingResult.hasErrors()){
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setMessage("Failed");
                responseDTO.setDetail(errorMessages);
                return ResponseEntity.badRequest().body(responseDTO);
            }
            return ResponseEntity.ok(customUserDetailService.loadUserByUsername(userDTO));
        }catch (Exception ex){
            log.error("Error login", ex);
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


}
