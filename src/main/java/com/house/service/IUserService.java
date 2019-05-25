package com.house.service;

import com.house.entity.User;

public interface IUserService {
    User findUserByName(String name);
}
