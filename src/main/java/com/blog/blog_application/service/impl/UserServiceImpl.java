package com.blog.blog_application.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog_application.errors.ResourceNotFoundException;
import com.blog.blog_application.model.User;
import com.blog.blog_application.payload.UserDto;
import com.blog.blog_application.repository.UserRepo;
import com.blog.blog_application.service.UserService;

import org.modelmapper.ModelMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo repository;

    @Autowired
    private ModelMapper modalMapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.repository.findAll(); // collect all user and convert to userDto and then convert into list
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " id", id));

        return this.userToDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto); // convert to user object
        User savedUser = this.repository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto UpdateUser(UserDto userDto, Integer id) {
        // get user based on id and if not found than throw custom error 
        User user = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        // if user is found than update with new value
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        // save updated user and return it after converting into DTO
        User updatedUser = this.repository.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        this.repository.delete(user);
    }

    private User dtoToUser(UserDto user) {
        User u = this.modalMapper.map(user, User.class);
        // u.setName(user.getUsername());
        // u.setEmail(user.getEmail());
        // u.setPassword(user.getPassword());
        // u.setType(user.getType());
        return u;
    }

    private UserDto userToDto(User user) {
        UserDto u = this.modalMapper.map(user, UserDto.class);
        // u.setUsername(user.getName());
        // u.setEmail(user.getEmail());
        // u.setPassword(user.getPassword());
        // u.setType(user.getType());
        return u;
    }

}
