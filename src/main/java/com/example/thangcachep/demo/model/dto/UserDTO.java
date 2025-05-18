package com.example.thangcachep.demo.model.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class UserDTO extends AbstractDTO {
    @NotBlank(message = "user name can not be Blank!")
    private String userName;
    private String fullName;
    @NotBlank(message = "password can not be Blank!")
    private String password;
    private Integer status;
    private List<RoleDTO> roles = new ArrayList<>();
    private String roleName;
    private String roleCode;
    private Map<String,String> roleDTOs = new HashMap<>();


}