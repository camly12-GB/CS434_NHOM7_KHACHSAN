package com.example.thangcachep.demo.security.utils;



import com.example.thangcachep.demo.model.dto.MyUserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {

    public static MyUserDetail getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Kiểm tra xem principal có phải là MyUserDetail không
        if (principal instanceof MyUserDetail) {
            return (MyUserDetail) principal;
        }

        // Nếu principal là String (username), tạo MyUserDetail mới với id mặc định hoặc lấy id từ database
        if (principal instanceof String) {
            // Nếu cần, bạn có thể lấy ID của người dùng từ cơ sở dữ liệu ở đây
            return new MyUserDetail( (String) principal, "", true, true, true, true, new ArrayList<>());
        }

        return null;
    }

    public static List<String> getAuthorities() {
        List<String> results = new ArrayList<>();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
        return results;
    }
}