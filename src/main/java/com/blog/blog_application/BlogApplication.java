package com.blog.blog_application;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.blog_application.model.User;
import com.blog.blog_application.payload.UserDto;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		// Prevent ModelMapper from mapping 'id' when converting DTO -> Entity
		modelMapper.typeMap(UserDto.class, User.class)
				.addMappings(mapper -> mapper.skip(User::setId));

		return modelMapper;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("abc"));
	}

}
