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

@Table(name = "vaitro")
public class RoleEntity   {

    private static final long serialVersionUID = -6525302831793188081L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VAITRO_ID")
    private Long id;

    @Column(name="tenvaitro", nullable = false)
    private String name;

    @Column(name="mota", nullable = false)
    private String describe;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<UserEntity> user = new ArrayList<>();

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

//    @OneToMany(mappedBy="roles",fetch = FetchType.LAZY)
//    private List<UserRoleEntity> userRoleEntities = new ArrayList<>();



}