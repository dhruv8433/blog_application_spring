package com.blog.blog_application.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.blog.blog_application.payload.ApiResponse;
import com.blog.blog_application.payload.UserDto;
import com.blog.blog_application.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/user") // <--- Ensure this matches the endpoint you are calling
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping // Call this using POST /api/user
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable Integer id, @RequestBody UserDto userDto) {
        UserDto updatedUser = this.userService.UpdateUser(userDto, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        this.userService.deleteUser(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        UserDto user = this.userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
    
}
