package com.blog.blog_application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.blog_application.errors.ResourceNotFoundException;
import com.blog.blog_application.model.User;
import com.blog.blog_application.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // loading user from database by user
        User user = this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("user", "email"  + username, 0));

        return user;
    }
    
}
 