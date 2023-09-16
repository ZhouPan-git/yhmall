package com.yhmall.service;

import com.yhmall.bean.User;

public interface UserService {
    User reg(User user);
    User findUserByUname(String uname);
}
