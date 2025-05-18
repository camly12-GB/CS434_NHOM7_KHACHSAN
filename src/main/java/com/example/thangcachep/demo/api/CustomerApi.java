package com.example.thangcachep.demo.api;

import com.example.thangcachep.demo.model.dto.CustomerDTO;
import com.example.thangcachep.demo.model.dto.ResponseDTO;
import com.example.thangcachep.demo.service.impl.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerApi {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/contact")
    public ResponseEntity<?> addContact(@Valid @RequestBody CustomerDTO customerDTO, BindingResult bindingResult) {
        log.info("add contact with: {}", customerDTO.getName());
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                ResponseDTO responseDTO = new ResponseDTO();
                responseDTO.setMessage("Failed");
                responseDTO.setDetail(errorMessages);
                return ResponseEntity.badRequest().body(responseDTO);
            }
            customerService.addCustomerContact(customerDTO);
            return ResponseEntity.ok("add contact successfully");
        } catch (Exception ex) {
            log.error("Error creating contact", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error create contact");
        }
    }


}
