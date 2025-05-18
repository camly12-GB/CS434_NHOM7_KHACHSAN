package com.example.thangcachep.demo.repository.custom.impl;


import com.example.thangcachep.demo.entity.UserEntity;
import com.example.thangcachep.demo.repository.UserRepository;
import com.example.thangcachep.demo.repository.custom.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> findByRole(String roleCode) {
       String sql = "FROM UserEntiy";
        Query query = entityManager.createNativeQuery(sql,UserEntity.class);
        return query.getResultList();
    }

    @Override
    public List<UserEntity> getAllUsers(Pageable pageable) {

        StringBuilder sql = new StringBuilder(buildQueryFilter())
                .append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        System.out.println("Final query: " + sql.toString());

        Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem() {
        String sql = buildQueryFilter();
        Query query = entityManager.createNativeQuery(sql.toString());
        return query.getResultList().size();
    }

    private String buildQueryFilter() {
        String sql = "SELECT * FROM user u WHERE u.status = 1";
        return sql;
    }
}
