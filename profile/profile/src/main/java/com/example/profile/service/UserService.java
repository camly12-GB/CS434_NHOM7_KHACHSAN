package com.example.profile.service;

import com.example.profile.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private User currentUser = new User(
            "Long Lê",
            "1234567890",
            "0123456***",
            "lehbaolong@dtu.edu.vn",
            "Số 03, Quang Trung, Thạch Thang, Hải Châu, Đà Nẵng"
    );

    public User getUser() {
        return currentUser;
    }

    public void updateUser(User user) {
        this.currentUser = user;
    }
}
