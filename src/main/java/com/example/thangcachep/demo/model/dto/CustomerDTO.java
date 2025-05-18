package com.example.thangcachep.demo.model.dto;

import com.example.thangcachep.demo.entity.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO extends AbstractDTO {
    private Long id;
    @NotBlank(message = "name can be not Blank")
    private String name;
    @NotBlank(message = "phone can be not Blank")
    private String phone;
    private String address;
    private String demand;
    @NotBlank(message = "email can be not Blank")
    private String email;
    private Long status;

}
