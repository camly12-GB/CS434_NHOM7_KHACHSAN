package com.example.thangcachep.demo.entity;

import com.example.thangcachep.demo.security.utils.SecurityUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "khachhang")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "makh")
    private Long id;

    @Column(name = "tenkh", nullable = false)
    private String name;

    @Column(name = "sodt", nullable = false)
    private String phone;

   @Column(name = "diachi")
    private String address;

   @Column(name = "yeucau")
    private String demand;

   @Column(name = "email", nullable = false)
    private String email;

   @Column(name = "trangthaitk", nullable = false)
    private Long status;

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

   @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "kh_nguoidung",
    joinColumns = @JoinColumn(name = "makh", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "nguoidung_id",nullable = false))
    private List<UserEntity> userEntities = new ArrayList<>();

}
