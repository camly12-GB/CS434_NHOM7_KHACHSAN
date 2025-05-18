package com.example.thangcachep.demo.entity;

import com.example.thangcachep.demo.security.utils.SecurityUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter

@Table(name = "nguoidung")
public class UserEntity  {

    private static final long serialVersionUID = -4988455421375043688L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nguoidung_id")
    private Long id;

    @Column(name = "tendangnhap", nullable = false, unique = true)
    private String userName;

    @Column(name = "tennguoidung", unique = true)
    private String fullName;

    @Column(name = "matkhau", nullable = false)
    private String password;

    @Column(name = "trangthaitk", nullable = false)
    private Long status;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "nguoidung_vaitro",
            joinColumns = @JoinColumn(name = "nguoidung_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "vaitro_id", nullable = false))
    private List<RoleEntity> roles = new ArrayList<>();

    @ManyToMany(mappedBy="userEntities", fetch = FetchType.LAZY)
    private List<CustomerEntity> customerEntities = new ArrayList<>();

    @Column(name = "createddate")
    @CreatedDate
    private Date createdDate;

    @Column(name = "createdby")
    @CreatedBy
    private String createdBy;

    @Column(name = "modifieddate")
    @LastModifiedDate
    private Date modifiedDate;

    @Column(name = "modifiedby")
    @LastModifiedBy
    private String modifiedBy;

    @PrePersist
    public void prePersist() {
        this.createdDate = new Date();
        this.createdBy = SecurityUtils.getPrincipal().getUsername();
        this.modifiedBy = null;
        this.modifiedDate = null;
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedDate = new Date();
        this.modifiedBy = SecurityUtils.getPrincipal().getUsername();
    }

}