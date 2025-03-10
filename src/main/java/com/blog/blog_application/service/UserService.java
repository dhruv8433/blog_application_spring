package com.blog.blog_application.service;

import java.util.List;

import com.blog.blog_application.payload.UserDto;


public interface UserService {
    List<UserDto> getAllUsers(); // get all users

    UserDto getUserById(Integer id); // get user based on id

    UserDto createUser(UserDto user); // create new user

    UserDto UpdateUser(UserDto user, Integer id); // update user based on id

    void deleteUser(Integer id); // delete user based on id
}
